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

    //全局可见
    fun eat() {}
}