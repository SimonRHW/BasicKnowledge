package com.simon.kotlin.example

/**
 * @author Simon
 * @desc
 */
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() {
    runBlocking {
        val channel = Channel<Int>(10)
        val producer = launch {
            for (i in 0..9) {
                println("Producing $i")
                delay(1000)
                channel.send(i)
            }
        }
        val consumer = launch {
            repeat(10) {
                val value = channel.receive()
                println("Consuming $value")
            }
        }
        producer.join()
        consumer.join()
    }
}
