package com.simon.kotlin.bean

class Users {
    var name: String = "simon"
    val age:Int =1
    var surname: String = ""
        get() = field.toUpperCase()

    var isEmail: Boolean = false

    var email: String = ""
        set(value) {
            if (isEmailValid(value)) field = value
        }

    private fun isEmailValid(value: String): Boolean {
        return isEmail
    }
}

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