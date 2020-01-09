package com.simon.kotlin.example

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 在上面的代码中，我们启动了10万个协程去执行了一个输出“Hello”的操作，
 * 当我们执行这段代码之后，“Hello”就会被陆续地打印出来。
 * 但是，如果我们启动10万个线程去做的话，就可能会出现“out of memory”的错误了。
 */
fun main() {
    runBlocking {
        repeat(100_032) {
            launch {
                println("Hello ${it}")
            }
        }
    }
}
