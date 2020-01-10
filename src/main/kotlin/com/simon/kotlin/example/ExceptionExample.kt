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
    throw RuntimeException("haha")
}

fun throwException2() {
    throw RuntimeException("hehe")
}