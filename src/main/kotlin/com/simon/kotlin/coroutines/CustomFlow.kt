package com.simon.kotlin.coroutines

fun main(){
    flowOf("Custom flow!")
        .map { value -> "$value Modified1" }
        .map { value -> "$value Modified2" }
        .map { value -> "$value Modified3" }
        .collect(object : Collector<String> {
            override fun emit(value: String) {
                println(value)
            }
        })
}

private inline fun <T, R> Flow<T>.map(crossinline transform: (value: T) -> R): Flow<R> {
    val upstream = this
    return object : Flow<R> {
        override fun collect(collector: Collector<R>) {
            upstream.collect(object : Collector<T> {
                override fun emit(value: T) {
                    val newValue = transform.invoke(value)
                    collector.emit(newValue)
                }
            })
        }
    }
}

fun flowOf(value: String): Flow<String> = object : Flow<String> {
    override fun collect(collector: Collector<String>) {
        collector.emit(value)
    }
}


interface Collector<in T> {
    fun emit(value: T)
}

interface Flow<out T> {
    fun collect(collector: Collector<T>)
}