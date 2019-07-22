package com.simon.kotlin

fun main(){

    println("Hello World")

    Test("no bug ").result()

}


class Test (val  msg:String){

    fun result(){
        println(msg)
    }

}