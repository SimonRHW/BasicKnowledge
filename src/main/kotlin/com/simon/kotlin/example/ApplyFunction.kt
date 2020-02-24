package com.simon.kotlin.example

import com.simon.kotlin.bean.User

/**
 * apply 函数：返回对象本身
 * 1、调用同一个对象的多个方法 / 属性时，可以省去对象名重复，直接调用方法名 / 属性即可
 * 2、定义一个变量在特定作用域内
 * 3、可以统一做判空处理
 */
fun main() {
    val user = User()
    user.name = "simon"
    user.surname = "ren"
    user.email = "simon@gmail.com"
    val result = user.apply {
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




