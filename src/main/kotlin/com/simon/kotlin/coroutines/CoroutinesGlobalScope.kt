package com.simon.kotlin.coroutines

import kotlinx.coroutines.*

/**
 *
 * delay 是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程，并且只能在协程中使用
 *
 * 调用了runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕
 */
fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
        println("launch time :${System.currentTimeMillis()}")
    }
    println("Hello,") // 协程已在等待时主线程还在继续
    println("main time :${System.currentTimeMillis()}")
    runBlocking {     // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    }
    println("runBlocking time :${System.currentTimeMillis()}")
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    println("sleep time :${System.currentTimeMillis()}")
}