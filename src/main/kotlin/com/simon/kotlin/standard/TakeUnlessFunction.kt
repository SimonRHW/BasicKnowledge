package com.simon.kotlin.standard

/**
 * 与takeIf相反
 *
 * 符合predicate 返回null，不符合则返回自身继续执行
 *
 */
fun main() {
    val i = 0
    val inc = i.takeUnless {
        val inc = it.inc()
        println(inc)
        it > 0
    }?.inc()
    println(inc)
}