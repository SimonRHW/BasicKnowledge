package com.simon.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
/** * 打印Job的状态信息 */
fun Job.log() {
    println(""" isActive = $isActive isCancelled = $isCancelled isCompleted = $isCompleted """.trimIndent())
}

//                        挂起函数能可以访问协程上下文吗？
//                                 ↓
suspend fun testContext() = coroutineContext

fun main() = runBlocking {
    println(testContext())
}