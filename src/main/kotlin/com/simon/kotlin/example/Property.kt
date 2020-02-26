package com.simon.kotlin.example

import com.simon.kotlin.bean.Users

/**
 * @author Simon
 * @date 2019/8/14
 * Function:
 */
fun main(){
    val user = Users()
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