package com.simon.kotlin.coroutines

import com.simon.java.suspendcps.JavaWithContinuation
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/** * 打印Job的状态信息 */
fun Job.log() {
    println(""" isActive = $isActive isCancelled = $isCancelled isCompleted = $isCompleted """.trimIndent())
}

fun main() = runBlocking {
    val javaWithContinuation = JavaWithContinuation()
    javaWithContinuation.testGetUserInfoAsync()
    javaWithContinuation.testGetUserInfoJFlow()
    javaWithContinuation.testGetUserInfoCPS()
    delay(10000)
}