package com.simon.kotlin.bean

class User {

    val id: String = ""
    var name: String = ""
    var isEmail: Boolean = false
    var surname: String = ""
        get() = field.toUpperCase()

    var email: String = ""
        set(value) {
            if (isEmailValid(value)) field = value
        }

    private fun isEmailValid(value: String): Boolean {
        return isEmail
    }
}