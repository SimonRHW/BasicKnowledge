package com.simon.kotlin.standard


class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting $name")
    }
}

fun main(args: Array<String>) {
    val greeter = Greeter(greeting = "Welcome")
    greeter(name = "Kotlin")
    //this calls the invoke function which takes String as a parameter
}