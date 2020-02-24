package com.simon.kotlin.example

import com.simon.kotlin.bean.User

/**
 * @author Simon
 * @date 2019/8/14
 * Function:
 */
fun main(){
    val user = User()
    user.name = "simon"
    user.surname = "ren"
    user.email= "simon@gmail.com"
    println(user.name)
    println(user.surname)
    println("${user.email}one")
    user.isEmail  =true
    user.email= "ren@gmail.com"
    println("${user.email}two")
}