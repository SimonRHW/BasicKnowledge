package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
//        StateFlow就是一个replaySize=1的sharedFlow,同时它必须有一个初始值，
//        此外，每次更新数据都会和旧数据做一次比较，只有不同时候才会更新数值。

//        StateFlow重点在状态，ui永远有状态，所以StateFlow必须有初始值，
//        同时对ui而言，过期的状态毫无意义，所以stateFLow永远更新最新的数据（和liveData相似），
//        所以必须有粘滞度=1的粘滞事件，让ui状态保持到最新。
//        另外在一个时间内发送多个事件，不会管中间事件有没有消费完成都会执行最新的一条.(中间值会丢失)
//
        val stateFlow = MutableStateFlow<Int>(value = -1)
        launch {
            stateFlow.collect {
                println("collect1 received ago state flow $it")
            }
        }
        launch {
            (1..3).forEach {
                println("  send before  flow $it")
                stateFlow.emit(it)
                println(" send after flow $it")
            }
        }
        // wait a minute
        delay(100)
        launch {
            stateFlow.collect {
                println("collect2 received state flow $it")
            }
        }
    }
}
