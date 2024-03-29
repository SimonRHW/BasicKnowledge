package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.System.currentTimeMillis

fun main() = runBlocking<Unit> {
    val nums = (1..3).asFlow().onEach { delay(300) } // 发射数字 1..3，间隔 300 毫秒
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // 每 400 毫秒发射一次字符串
    val startTime = currentTimeMillis() // 记录开始的时间
    nums.zip(strs) { a, b -> "$a -> $b" } // 使用“zip”组合单个字符串
        .collect { value -> // 收集并打印
            println("$value at ${currentTimeMillis() - startTime} ms from zip start")
        }

    nums.combine(strs) { a, b -> "$a -> $b" }
        .collect { value ->
            println("$value at ${currentTimeMillis() - startTime} ms from combine start")
        }
}