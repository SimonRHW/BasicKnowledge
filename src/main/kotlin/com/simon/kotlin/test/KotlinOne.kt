package com.simon.kotlin.test

fun main() {
    val string = "I am Kotlin"
    val int = 1234
    val long = 1234L
    val float = 1234f
    val double = 12.34
    val double2 = 10.1e6

    printlnClassName(string)
    printlnClassName(int)
    printlnClassName(long)
    printlnClassName(float)
    printlnClassName(double)
    printlnClassName(double2)

    val sum = sum(2, 3)
    print(sum)
}

fun printlnClassName(msg: Any) {
    println(msg.javaClass.name)
}

fun sum(x: Int, y: Int): Int {
    return x + y
}
