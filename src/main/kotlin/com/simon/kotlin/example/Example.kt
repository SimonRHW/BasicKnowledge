package com.simon.kotlin.example

import java.lang.Exception

fun main() {
    try {
        throwException()
        throwException2()
    } catch (ex: Exception) {
        println(ex.message)
    }
    println("111")
}


fun throwException() {
    var ex = RuntimeException("haha")
    throw ex
}
fun throwException2() {
    var ex = RuntimeException("hehe")
    throw ex
}