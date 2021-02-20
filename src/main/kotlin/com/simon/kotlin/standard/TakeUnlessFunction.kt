package com.simon.kotlin.standard

/**
 * 与takeIf相反
 *
 * 符合predicate 返回null，不符合则返回自身继续执行
 * https://louis383.medium.com/kotlin-takeif-%E5%92%8C-takeunless-8c43b7d3797e
 */
fun main() {
    val i = 0
    val inc = i.takeUnless {
        val inc = it.inc()
        println(inc)
        println(it)
//        inc > 0
        inc > 1
    }?.inc()
    println(inc)
}