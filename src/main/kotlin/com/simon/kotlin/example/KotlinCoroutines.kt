package com.simon.kotlin.example

import kotlinx.coroutines.*

suspend fun main() = coroutineScope {
    try {
        val task1 = async { fetchResult(id = 36) }
        val task2 = async { fetchResult2(id = 72) }
        val results = awaitAll(task1, task2)
        log(results)
    } catch (ex: Exception) {
        println(ex.message)
        println("1111")
    }
    unConfinedTest()
    println("hehihie")
}

suspend fun fetchResult(id: Int): String {
    log("started request #$id")
    delay(500)
    return withContext(Dispatchers.IO) {
        throw RuntimeException("hahaha")
        "result #$id"
    }
}

suspend fun fetchResult2(id: Int): String {
    log("started request #$id")
    delay(500)
    return withContext(Dispatchers.Main) {
        throw RuntimeException("hehehe")
        "result #$id"
    }
}

val startedAt = System.currentTimeMillis()
fun elapsed() = System.currentTimeMillis() - startedAt
fun log(message: Any) = println("At ${elapsed()} ms: $message")


suspend fun unConfinedTest() {
    withContext(Dispatchers.Unconfined) {
        println(1)
        withContext(Dispatchers.Unconfined) {
            // Nested unconfined
            println(2)
        }
        println(3)
    }
    println("Done")
}

