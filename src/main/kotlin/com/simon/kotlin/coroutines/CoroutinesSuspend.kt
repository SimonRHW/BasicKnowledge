package com.simon.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import kotlinx.coroutines.jdk9.flowPublish
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Flow
import kotlin.coroutines.Continuation

/**
 * 1.
 * 要定义挂起函数，我们只需在普通函数的基础上，增加一个 suspend 关键字。suspend 这个关键字，是会改变函数类型的，
 * “suspend (Int) -> Double”与“(Int) -> Double”并不是同一个类型。
 * 2.
 * 挂起函数，由于它拥有挂起和恢复的能力，因此对于同一行代码来说，“=”左右两边的代码分别可以执行在不同的线程之上。
 * 而这一切，都是因为 Kotlin 编译器这个幕后的翻译官在起作用。
 * 3.
 * 挂起函数的本质，就是 Callback。只是说，Kotlin 底层用了一个更加高大上的名字，叫 Continuation。
 * 而 Kotlin 编译器将 suspend 翻译成 Continuation 的过程，则是 CPS 转换。
 * 这里的 Continuation 是代表了，“程序继续运行下去需要执行的代码”，“接下来要执行的代码”，或者是 “剩下的代码”。
 */
fun main() {
    runBlocking {
        val user = getUserInfo()
        val friendList = getFriendList(user)
        val feedList = getFeedList(friendList)
    }
}

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "BoyCoder"
}

//CPS 转换（Continuation-Passing-Style Transformation）
//CPS 转换的过程中，函数的类型发生了变化：“suspend ()->String” 变成了 “(Continuation)-> Any?”。
fun getUserInfoCPS(continuation: Continuation<String>): Any {
    continuation.resumeWith(Result.success("BoyCoder"))
    return Unit
}

fun getUserInfoAsync(): CompletableFuture<String> =
    GlobalScope.future {
        getUserInfo()
    }

fun getUserInfoJFlow(): Flow.Publisher<String> =
    flowPublish {
        getUserInfo()
    }

suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "Tom, Jack"
}

suspend fun getFeedList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "{FeedList..}"
}

fun func1(num: Int): Double {
    return num.toDouble()
}

suspend fun func2(num: Int): Double {
    delay(100L)
    return num.toDouble()
}

val f1: (Int) -> Double = ::func1
val f2: suspend (Int) -> Double = ::func2
