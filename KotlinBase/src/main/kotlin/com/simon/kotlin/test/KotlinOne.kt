package com.simon.kotlin.test

fun main(){

    println("Hello World")

    Test("no bug ").result()

    val language = "kotlin"

    val text = "$language has ${language.length} characters"

    println(text)

}

class Test (val  msg:String){
    fun result(){
        println(msg)
    }
}
