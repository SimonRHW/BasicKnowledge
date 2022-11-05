package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val nums = (1..3).asFlow() // numbers 1..3
    val strs = flowOf("one", "two", "three")
    nums.zip(strs) { a, b -> "$a -> $b" }
        .collect { println(it) }
}