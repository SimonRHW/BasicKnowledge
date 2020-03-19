package com.simon.kotlin.coroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    //会阻塞后面的main代码执行
    runBlocking {
        val request = launch {
            val job1 = launch {
                println("启动协程1 time :${System.currentTimeMillis()}")
                println("job1 执行的线程 =${Thread.currentThread().name}")
                for (i in 1..3) {
                    delay(100L)
                    println("job1:" + i)
                }
                println("协程1执行完成 time :${System.currentTimeMillis()}")
            }

            val job2 = launch() {
                println("启动协程2 time :${System.currentTimeMillis()}")
                println("job2 执行的线程=${Thread.currentThread().name}")
                for (i in 1..3) {
                    delay(100L)
                    println("job2:" + i)
                }
                println("协程2执行完成 time :${System.currentTimeMillis()}")
            }
            //step 1
            println("request :执行的线程=${Thread.currentThread().name} :time=${System.currentTimeMillis()}")
            for (i in 1..3) {
                delay(100L)
                println("request:" + i)
            }
            println("request执行完成 time :${System.currentTimeMillis()}")
        }

        //step 0
        println("runBlocking :执行的线程=${Thread.currentThread().name} :time=${System.currentTimeMillis()}")
        for (i in 1..3) {
            delay(100L)
            println("runBlocking:" + i)
        }
        println("runBlocking执行完成 time :${System.currentTimeMillis()}")
        delay(900L)
        request.cancel()
        request.cancelAndJoin()
        delay(2000L)
    }
    println("main :执行的线程=${Thread.currentThread().name} :time=${System.currentTimeMillis()}")
}