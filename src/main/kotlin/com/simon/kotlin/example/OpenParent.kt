package com.simon.kotlin.example

open class OpenParent {
    open var x = 10
}

class SubClass : OpenParent() {
    override var x = 20
    fun println() {
        println("x = ${x}")
        println("super.x = ${super.x}")
    }
}

fun main() {
    println(SubClass().println())
}