package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        //不管是否订阅，流都会发送
        // SharedFlow侧重在事件，当某个事件触发，发送到队列之中，按照挂起或者非挂起、缓存策略等将事件发送到接受方，
        // 在具体使用时，SharedFlow更适合通知ui界面的一些事件，比如toast等，也适合作为viewModel和repository之间的桥梁用作数据的传输。

        val sharedFlow = MutableSharedFlow<Int>(
            replay = 1,//相当于粘性数据 为0时 collect2收集不到数据
            extraBufferCapacity = 0,//接受的慢时候，发送的入栈
            onBufferOverflow = BufferOverflow.SUSPEND
        )
        launch {
            sharedFlow.collect {
                println("collect1 received ago shared flow $it")
            }
        }

        launch {
            (1..3).forEach {
                println("emit1  send ago  flow $it")
                sharedFlow.emit(it)
                println("emit1 send after flow $it")
            }
        }
        delay(100)

        launch {
            sharedFlow.collect {
                println("collect2 received shared flow $it")
            }
        }
    }
}
