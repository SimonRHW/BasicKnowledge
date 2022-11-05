package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * 需要等待所有值才能处理它们
 */
suspend fun getValues(): List<Int> {
    delay(2000)
    return listOf(1, 2, 3)
}

fun processValues() {
    runBlocking {
        val values = getValues()
        for (value in values) {
            println(value)
        }
    }
}

/**
 * 惰性的不用等待所有值，会有一个消费一个
 */
suspend fun getSequenceValues(): Sequence<Int> = sequence {
    Thread.sleep(1000)
    yield(1)
    Thread.sleep(1000)
    yield(2)
    Thread.sleep(1000)
    yield(3)
}

fun processSequenceValues() {
    runBlocking {
        val values = getSequenceValues()
        for (value in values) {
            println(value)
        }
    }
}

val getFlowValues = flowOf("A", "B", "C", "D")
fun processFlowValues() {
    runBlocking {
        getFlowValues
            .map {
                "map:$it"
            }
            .filter {
                !it.contains("B")
            }.collect {
                //冷流需要终端操作符才会产生值
                println(it)
            }
    }
}

fun main() {
//    processValues()
//    processSequenceValues()
//    processFlowValues()
    processChannelValues()
}

fun processChannelValues() {
    runBlocking {
        val channel = getProducer(this)
        testConsumer(channel)
    }
}

fun getProducer(scope: CoroutineScope) = scope.produce {
    println("Send:1")
    send(1)
    println("Send:2")
    send(2)
    println("Send:3")
    send(3)
    println("Send:4")
    send(4)
}

suspend fun testConsumer(channel: ReceiveChannel<Int>) {
    delay(100)
    val i = channel.receive()
    println("Receive$i")
    delay(100)
    val j = channel.receive()
    println("Receive$j")
    delay(100)
    val k = channel.receive()
    println("Receive$k")
    delay(100)
    val m = channel.receive()
    println("Receive$m")
}




