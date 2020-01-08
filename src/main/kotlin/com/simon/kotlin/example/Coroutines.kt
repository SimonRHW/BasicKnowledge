package com.simon.kotlin.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val request = launch {
            val job1 = launch {
                println("启动协成1 :"+System.currentTimeMillis())
                println("job1 执行的线程=${Thread.currentThread().name}")
                delay(1000L)
                println("协成1执行完成 :"+System.currentTimeMillis())
            }

            val job2 = launch (coroutineContext){
                println("启动协成2 :"+System.currentTimeMillis())
                println("job2 执行的线程=${Thread.currentThread().name}")
                delay(1000L)
                println("协成2执行完成:"+System.currentTimeMillis())
            }
        }
//        delay(900L)
//        request.cancel()
//        delay(2000L)
    }
}