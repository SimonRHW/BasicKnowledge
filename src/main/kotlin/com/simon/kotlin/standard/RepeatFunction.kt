package com.simon.kotlin.standard

fun main(){
    repeat(3) {
        println("Hello")
    }

    // greets with an index
    repeat(3) { index ->
        println("Hello with index $index")
    }

    repeat(0) {
        error("We should not get here!")
    }
}