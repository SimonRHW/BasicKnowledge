package com.simon.kotlin.standard

/**
 * takeIf 是一个inline函数，传入T返回Boolean的函数
 *
 * 符合predicate 返回自身继续执行，否则返回null
 *
 */
fun main() {
    val i = 0
    val inc = i.takeIf {
        it > 0
    }?.inc()
    println(inc)

    val inc2 = i.takeIf {
        it >=0
    }?.inc()
    println(inc2)
}