package com.simon.kotlin.coroutines

import kotlinx.coroutines.*

/**
协程，可以理解为更加轻量的线程，成千上万个协程可以同时运行在一个线程当中；
协程，其实是运行在线程当中的轻量的 Task；
协程，不会与特定的线程绑定，它可以在不同的线程之间灵活切换。
//Help -> Edit Custom VM Options ： -Dkotlinx.coroutines.debug=on
//Run | Edit Configurations VM Options ： -Dkotlinx.coroutines.debug=on
 * 执行顺序：从外到内
runBlocking :执行的线程=main @coroutine#1 :time=1667445585386
request :执行的线程=main @coroutine#2 :time=1667445585410
启动job1线程 =main @coroutine#3;job1 time :1667445585411
启动job2线程 =main @coroutine#4;job2 time :1667445585411
runBlocking:1
request:1
job1:1
job2:1
runBlocking:2
request:2
job1:2
job2:2
runBlocking:3
runBlocking执行完成 time :1667445585728
request:3
request执行完成 time :1667445585731
job1:3
job1 finish time :1667445585733
job2:3
job2 finish time :1667445585733
main :执行的线程=main :time=1667445588640
 */
fun main() {
    exeStep()
//    testCoroutineWithThread()
}

private fun exeStep() {
    //会阻塞后面的main代码执行
    runBlocking {
        val request = launch {
            val job1 = launch {
                //step3
                println("启动job1线程 =${Thread.currentThread().name};job1 time :${System.currentTimeMillis()}")
                for (i in 1..3) {
                    delay(100L)
                    println("job1:" + i)
                }
                println("job1 finish time :${System.currentTimeMillis()}")
            }

            val job2 = launch() {
                //step4
                println("启动job2线程 =${Thread.currentThread().name};job2 time :${System.currentTimeMillis()}")
                for (i in 1..3) {
                    delay(100L)
                    println("job2:" + i)
                }
                println("job2 finish time :${System.currentTimeMillis()}")
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

private fun testCoroutineWithThread() = runBlocking(Dispatchers.IO) {
    repeat(3) {
        launch {
            repeat(3) {
                println(Thread.currentThread().name)
                delay(100)
            }
        }
    }

    delay(5000L)
}