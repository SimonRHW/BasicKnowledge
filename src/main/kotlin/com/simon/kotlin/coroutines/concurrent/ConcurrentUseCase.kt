package com.simon.kotlin.coroutines.concurrent

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executors

/**
 * 当我们在协程中面临并发问题的时候，首先可以考虑：是否真的需要多线程？如果不需要的话，其实是可以不考虑多线程同步问题的。
 *
 * 多线程并发，往往会有共享的可变状态，而共享可变状态的时候，就需要考虑同步问题
 *
 * 第一种手段，单线程并发，在 Java 世界里，并发往往意味着多线程，但在 Kotlin 协程当中，我们可以轻松实现单线程并发，这时候我们就不用担心多线程同步的问题了。
 *
 * 第二种手段，Kotlin 官方提供的协程同步锁，Mutex，由于它的 lock 方法是挂起函数，所以它跟 JDK 当中的锁不一样，Mutex 是非阻塞的。
 * 需要注意的是，我们在使用 Mutex 的时候，应该使用 withLock{} 这个高阶函数，而不是直接使用 lock()、unlock()。
 *
 * 第三种手段，Kotlin 官方提供的 Actor，这是一种普遍存在的并发模型。
 * 在目前的版本当中，Kotlin 的 Actor 只是 Channel 的简单封装，它的 API 会在未来的版本发生改变。
 *
 * 第四种手段，借助函数式思维。我们之所以需要处理多线程同步问题，主要还是因为存在共享的可变状态。
 * 其实，共享可变状态，既不符合无副作用的特性，也不符合不变性的特性。当我们借助函数式编程思维，实现无副作用和不变性以后，并发代码也会随之变得安全
 */

fun main() = run {
    noConcurrent()
    concurrentSynchronized()
    concurrentSingleThread()
    concurrentMutex()
    concurrentActor()
    concurrentImmutable()
    concurrentImmutableFunctional()
}

fun noConcurrent() = runBlocking {
    var i = 0

    // Default 线程池
    launch(Dispatchers.Default) {
        repeat(100000) {
            i++
        }
    }

    delay(1000L)
    println("noConcurrent i = $i")
}

/**
 * synchronized(){} 当中调用挂起函数，编译器会报错
 * 因为挂起函数会被翻译成带有 Continuation 的异步函数，从而就造成了 synchronid 代码块无法正确处理同步
 */
fun concurrentSynchronized() = runBlocking {
    var i = 0
    val jobs = mutableListOf<Job>()
    val lock = Any()
    suspend fun prepare() {
        // 模拟准备工作
    }
    // 重复十次
    repeat(100) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                synchronized(lock) {
//                    prepare()
                    i++
                }
            }
        }
        jobs.add(job)
    }

    // 等待计算完成
    jobs.joinAll()
    println("concurrentSynchronized i = $i")
}

/**
 * 单线程并发"是一个伪概念
 * 在实际项目中delay这种延时任务往往是io操作，把这里delay替换为file.readText去读一个10M的文件，
 * 那个三个协程都用同一个mySingleDispatcher的话其实是串行执行的，并没与起到优化并行的效果，
 * 所以如果要说协程的优越性，这里还是得使用IO线程池既Dispatcher.IO。这样可以把多IO任务同时启动，达到并行优化，
 * 否则，同一个线程，协程无法并行，时间消耗是三个IO动作之和
 */
fun concurrentSingleThread() = runBlocking {
    val mySingleDispatcher = Executors.newSingleThreadExecutor {
        Thread(it, "concurrentSingleThread").apply { isDaemon = true }
    }.asCoroutineDispatcher()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(100) {
        val job = launch(mySingleDispatcher) {
            repeat(1000) {
                i++
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("concurrentSingleThread i = $i")
}


/**
 * Mutex 对比 JDK 当中的锁，最大的优势就在于支持挂起和恢复
 * Mutex 是一个接口，它的 lock() 方法其实是一个挂起函数。而这就是实现非阻塞式同步锁的根本原因
 * mutex是挂起函数，那么它存在竞争的话是支持协程挂起，意味着底层线程资源可以复用，比起Java的线程并不会浪费多余的系统资源
 */
fun concurrentMutex() = runBlocking {
    val mutex = Mutex()

    var i = 0
    val jobs = mutableListOf<Job>()

    repeat(100) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                //mutex.lock()、mutex.unlock() 之间发生异常，会导致 mutex.unlock() 无法被调用
                //mutex.lock()
                mutex.withLock {
                    i++
                }
                //mutex.unlock()
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    println("concurrentMutex i = $i")
}


sealed class Msg
object AddMsg : Msg()

class ResultMsg(
    val result: CompletableDeferred<Int>,
) : Msg()

/**
 * Actor，其实是在很多编程语言当中都存在的一个并发同步模型。
 * 在 Kotlin 当中，也同样存在这样的模型，它本质上是基于 Channel 管道消息实现的
 * https://github.com/Kotlin/kotlinx.coroutines/issues/87
 */
fun concurrentActor() = runBlocking {

    suspend fun addActor() = actor<Msg> {
        var counter = 0
        for (msg in channel) {
            when (msg) {
                is AddMsg -> counter++
                is ResultMsg -> msg.result.complete(counter)
            }
        }
    }

    val actor = addActor()
    val jobs = mutableListOf<Job>()

    repeat(100) {
        val job = launch(Dispatchers.Default) {
            repeat(1000) {
                actor.send(AddMsg)
            }
        }
        jobs.add(job)
    }

    jobs.joinAll()

    val deferred = CompletableDeferred<Int>()
    actor.send(ResultMsg(deferred))

    val result = deferred.await()
    actor.close()

    println("concurrentActor i = ${result}")
}


/**
 * 避免共享可变状态
 * 相当于将 100000 次计算，平均分配给了 100 个协程，让它们各自计算 1000 次。
 * 这样一来，每个协程都可以进行独立的计算，然后我们将 100 个协程的结果汇总起来，最后累加在一起。
 *
 * 函数式编程当中，就是追求不变性、无副作用
 */

fun concurrentImmutable() = runBlocking {
    val deferreds = mutableListOf<Deferred<Int>>()

    repeat(100) {
        val deferred = async(Dispatchers.Default) {
            var i = 0
            repeat(1000) {
                i++
            }
            return@async i
        }
        deferreds.add(deferred)
    }

    var result = 0
    deferreds.forEach {
        result += it.await()
    }

    println("concurrentImmutable i = $result")
}

fun concurrentImmutableFunctional() = runBlocking {
    val result = (1..100).map {
        async(Dispatchers.Default) {
            var i = 0
            repeat(1000) {
                i++
            }
            return@async i
        }
    }.awaitAll()
        .sum()

    println("concurrentImmutableFunctional i = $result")
}