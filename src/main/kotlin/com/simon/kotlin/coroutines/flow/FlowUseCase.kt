package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * @author Simon
 * @desc
 */

fun main() = runBlocking {
    val flow = flow<Int> {
        (1..5).forEach {
            delay(1000)
            emit(it)
        }
    }
    //冷流，需要去触发收集，才能接收到结果
    flow.collect {
        println(" 第1个收集器: 我是冷流：$it")
    }
    flow.collect {
        println(" 第2个收集器: 我是冷流：$it")
    }

}

