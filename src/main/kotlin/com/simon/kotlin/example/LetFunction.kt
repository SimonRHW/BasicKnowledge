package com.simon.kotlin.example

import com.simon.kotlin.bean.Users

/**
 * let函数：返回值 = 最后一行 / return的表达式
 */

fun main() {
    val user = Users()
    user.name = "simon"
    user.surname = "ren"
    user.email = "simon@gmail.com"
    val result = user.let {
        println(it)
        println(it.name)
        println("${it.email}one")
        it.isEmail = true
        it.email = "ren@gmail.com"
        println("${it.email}two")
        test()
    }
    println(result)
}


fun test(): Boolean {
    return true
}


