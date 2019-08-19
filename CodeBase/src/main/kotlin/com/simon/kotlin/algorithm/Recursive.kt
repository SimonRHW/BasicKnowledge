package com.simon.kotlin.algorithm

fun main(){

    println(foo(0))
    println(foo(1))
    println(foo(2))
    println(foo(3))
}

fun foo( n : Int): Int {
    return if (n == 0) 1 else n * foo(n - 1)
}