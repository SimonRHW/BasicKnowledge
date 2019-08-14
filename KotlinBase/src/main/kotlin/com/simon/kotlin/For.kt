package com.simon.kotlin

/**
 * @author Simon
 * @date 2019/8/14
 * Function:
 */
fun main(){
    for (i in 1..100){
        println(i)
    }

    for (i in 100 downTo 1){
        println(i)
    }

    val array = arrayOf("a", "b" ,"c")

    for (i in 1 until array.size step 2){
        println(array[i])
    }

    for ((index,element) in array.withIndex()){
        println(index )
        println(element )
    }

    val map = mapOf(1 to "one",2 to "two")

    for ((key,value) in map){
        println(key)
        println(value)
    }
}