package com.simon.kotlin.example

import com.simon.kotlin.bean.User

/**
 * @author Simon
 * Desc ：验证入口
 */

fun main() {
    val user = User("test", 20, "hi")
    println(user.name)
}