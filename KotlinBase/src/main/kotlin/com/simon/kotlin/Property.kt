package com.simon.kotlin

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
    println(user.email)
    user.haha  =true
    user.email= "ren@gmail.com"
    println(user.email)
}

class User{

    val  id : String = ""
    var name:String =""
    var haha :Boolean = false

    var surname :String = ""
        get() = field.toUpperCase()

    var email :String = ""
    set(value) {
        if(isEmailValid(value)) field = value
    }

    private fun isEmailValid(value: String): Boolean {
        return haha
    }

}