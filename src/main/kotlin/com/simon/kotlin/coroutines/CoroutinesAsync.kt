package com.simon.kotlin.coroutines

import kotlinx.coroutines.*

suspend fun main() = coroutineScope {
    val task1 = async { fetchResult(id = 42) }
    val task2 = async { fetchResult(id = 99) }
    val results = awaitAll(task1, task2)
    log(results)
}

suspend fun fetchResult(id: Int): String {
    log("started request #$id")
    delay(500)
    return "result #$id"
}

val startedAt = System.currentTimeMillis()
fun elapsed() = System.currentTimeMillis() - startedAt
fun log(message: Any) = println("At ${elapsed()} ms: $message")

