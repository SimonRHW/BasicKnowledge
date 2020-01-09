package com.simon.kotlin.example

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val request = launch {
            val job1 = launch {
                println("启动协成1 time :${System.currentTimeMillis()}")
                println("job1 执行的线程 =${Thread.currentThread().name}")
                for (i in 1..10) {
                    delay(100L)
                    println("job1:" + i)
                }
                println("协成1执行完成 time :${System.currentTimeMillis()}")
            }

            val job2 = launch() {
                println("启动协成2 time :${System.currentTimeMillis()}")
                println("job2 执行的线程=${Thread.currentThread().name}")
                for (i in 1..10) {
                    delay(100L)
                    println("job2:" + i)
                }
                println("协成2执行完成 time :${System.currentTimeMillis()}")
            }

//            println("request :执行的线程=${Thread.currentThread().name} :time=${System.currentTimeMillis()}")
//            for (i in 1..10) {
//                delay(100L)
//                println("request:" + i)
//            }
        }

//        println("runBlocking :执行的线程=${Thread.currentThread().name} :time=${System.currentTimeMillis()}")
//        for (i in 1..10) {
//            delay(100L)
//            println("runBlocking:" + i)
//        }
//        delay(900L)
//        request.cancel()
//        request.cancelAndJoin()
//        delay(2000L)
    }
//    println("main :执行的线程=${Thread.currentThread().name} :time=${System.currentTimeMillis()}")
}