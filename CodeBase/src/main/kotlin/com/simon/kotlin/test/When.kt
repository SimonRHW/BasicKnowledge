package com.simon.kotlin.test

/**
 * @author Simon
 * @date 2019/8/14
 * Function:
 */

fun main(){
    println(Train(0))
    println(Train(1))
    println(Train(2))
    println(Train(6))
    println(Train(100))
    println(Train(1.333))
}
class Train (val  data : Number){
    override fun toString(): String {
        return when(data){
            null,0 ->"empty"
            1 -> "tiny"
            in  2..10 -> "small"
            is  Int -> "big int"
            else -> "$data"
        }
    }
}