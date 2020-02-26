package com.simon.kotlin.example

/**
 * @author Simon
 * Desc ：
 */
interface Behavior {
    val id: Int
        get() = 10

    fun eat()
    fun test() {
        println("test")
    }

    fun test2() {
        println("test2")
    }
}