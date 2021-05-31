package com.simon.kotlin.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Channel 是发送方(通过 SendChannel)和接收方(通过 receivevechannel)之间通信的非阻塞原语。
 * 从概念上讲，通道类似于 Java 的 BlockingQueue，但它具有挂起操作而不是阻塞操作，并且可以关闭
 * 其中一个不同是它代替了阻塞的 put 操作并提供了挂起的 send，还替代了阻塞的 take 操作并提供了挂起的 receive。
 *
 * Channel (capacity) factory 函数用于根据容量整数的值创建不同类型的通道:
 *
 * 和队列不同，一个通道可以通过被关闭来表明没有更多的元素将会进入通道。 在接收者中可以定期的使用 for 循环来从通道中接收元素。
 */
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
        for (x in 1..5) channel.send(x * x)
    }
    // 这里我们打印了 5 次被接收的整数：
    repeat(5) { println(channel.receive()) }
    println("Done!")

    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close() // 我们结束发送
    }
    // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
    for (y in channel) println(y)
    println("Done!")
}


