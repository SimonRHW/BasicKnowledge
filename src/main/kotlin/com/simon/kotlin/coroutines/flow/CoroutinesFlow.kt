package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

/**
 * 一种异步数据流，按顺序发出值并正常完成或带有异常
 *
 * 流上的中间操作符，如 map、 filter、 take、 zip 等，是应用于上游流或流的函数，并返回下游流，可以应用于其他操作符。
 * 中间操作不执行流中的任何代码，也不挂起函数本身。
 * 它们只是为将来的执行建立一个操作链，然后很快返回。这就是所谓的冷流特性。
 *
 * 流上的终端操作符可以挂起诸如 collect、 single、 reduce、 toList 等函数，也可以挂起启动给定范围内的流的收集的 launchIn 操作符。
 * 它们应用于所有操作的上游流和触发执行。流的执行也称为收集流，并且总是以挂起的方式执行，而没有实际的阻塞。
 * 终端操作员正常或异常地完成取决于上游所有流程操作的成功或失败执行。最基本的终端操作符是 collect
 *
 * 默认情况下，流是顺序的，所有的流操作都是在同一个协同程序中顺序执行的，只有一些特别设计来引入流执行并发性的操作例外，
 * 比如 buffer 和 flatMapMerge。详细信息请参阅他们的文档。
 *
 * Flow 接口并不携带流是否是可以重复收集并且每次收集时触发相同代码执行的冷流的信息，或者是否是从每个收集上的相同运行源发出不同值的热流的信息。
 * 流通常代表冷流，但是有一个 SharedFlow 子类型代表热流。此外，stateIn 和 shareIn 操作符可以将任何流转换为热流，
 * 或者通过 produceIn 操作符将流转换为热通道。
 *
 * Flow 接口的所有实现都必须遵循下面详细描述的两个关键属性:
 * Context preservation.
 * Exception transparency. 异常透明性
 *
 * Flow 是 Reactive Streams 兼容的，您可以安全地使用 Flow.asPublisher 和 Publisher.asFlow
 * 从 kotlinx-coroutines-Reactive 模块与 Reactive Streams 互操作。
 *
 *
 */
fun main() = runBlocking<Unit> {
    println("Calling simple function...")
    val flow = simple()
    println("Calling collect...")
    flow.collect { println(it) }
    flow.collect { println(it) }
    println("Calling collect again...")
    flow.collect { println(it) }

    var somePojo = SomePojo("Something Different")
    val mutableStateFlow = MutableStateFlow(somePojo)
    println("INITIAL: ${mutableStateFlow.value}")
    somePojo = somePojo.copy(name = "copy update")
    println("CURRENT: ${mutableStateFlow.value}")
    mutableStateFlow.value = somePojo
    println("UPDATED: ${mutableStateFlow.value}")
//    mutableStateFlow.collect {
//        println("collect: $it")
//    }
    somePojo = SomePojo("last copy")
    mutableStateFlow.emit(somePojo)
    println("LAST: ${mutableStateFlow.value}")
    mutableStateFlow.emit(SomePojo("last new"))
    println("END: ${mutableStateFlow.value}")
    mutableStateFlow.collect {
        println("collect: $it")
    }
}

fun simple(): Flow<Any> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

data class SomePojo(val name: String = "placeholder")


