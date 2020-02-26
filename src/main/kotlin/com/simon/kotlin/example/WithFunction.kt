package com.simon.kotlin.example

import com.simon.kotlin.bean.Users

/**
 * with函数：返回值 = 最后一行 / return的表达式
 * 调用同一个对象的多个方法 / 属性时，可以省去对象名重复，直接调用方法名 / 属性即可
 */
fun main() {
    val user = Users()
    user.name = "simon"
    user.surname = "ren"
    user.email = "simon@gmail.com"
    val result = with(user) {
        println(this)
        println(name)
        println("${email}one")
        isEmail = true
        email = "ren@gmail.com"
        println("${email}two")
        test()
    }
    println(result)
}




