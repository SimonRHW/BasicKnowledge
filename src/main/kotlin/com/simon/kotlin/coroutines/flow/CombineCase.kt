package com.simon.kotlin.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

data class RefreshEmit(val withLoading: Boolean = true) {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}

private val innerStringStateFlow = MutableStateFlow<String?>(null)
private val stateFlowString: StateFlow<String?> get() = innerStringStateFlow

fun setInnerStateFlowValue(systemUpdateInfo: String?) {
    println("setInnerStateFlowValue:$systemUpdateInfo")
    innerStringStateFlow.value = systemUpdateInfo
}

fun clearInnerStateFlowValue() {
    println("clearInnerStateFlowValue")
    innerStringStateFlow.value = null
}

fun getStringStateFlow(): StateFlow<String?> {
    return stateFlowString
}

class InvokeF() {
    operator fun invoke(): StateFlow<String?> {
        return getStringStateFlow()
    }
}

fun main() = runBlocking<Unit> {
    val refreshFlow: MutableSharedFlow<RefreshEmit> = MutableSharedFlow()

//    val refreshFlow = MutableStateFlow(RefreshEmit(true))
    val invokeF = InvokeF()
    launch {
        combine(refreshFlow, invokeF()) { refreshEmit, stateFlowString ->
            return@combine listOf(refreshEmit, stateFlowString)
        }.collectLatest { flow ->
            println("collectLatest flow $flow")
            val refreshEmit = flow[0] as RefreshEmit
            val stateFlowString = flow[1] as String?
            println("refreshEmit  $refreshEmit")
            println("systemUpdateInfo  $stateFlowString")
        }
    }
    launch {
        delay(1500)
        setInnerStateFlowValue("hello world")
        println("setInnerStateFlowValue")
        delay(1500)
        refreshFlow.emit(RefreshEmit(true))
        println("refreshFlow emit")
//        delay(1500)
//        clearInnerStateFlowValue()
    }
}