package com.simon.kotlin.example

fun main() {
    val arrayVal = intArrayOf(1, 2, 3)
    //Val cannot be reassigned
    //arrayVal = intArrayOf(2,3,4)
    println(arrayVal)
    println(arrayVal[0])
    arrayVal[0] = 5
    println(arrayVal)
    println(arrayVal[0])

    count(1)
    count(1)
    println(callVar(arrayVal.asList()))
    println(callVal(arrayVal.asList()))
    println(call(arrayVal.asList()))
}

var a = 1
fun count(i: Int) {
    a += 1
    println(i + a)
}

fun callVar(list: List<Int>): Int {  //5,2,3      5,10,12,36,39
    var res = 0;
    for (a in list) {
        res *= a
        res += a
    }
    return res
}

fun callVal(list: List<Int>): Int {
    fun recurse(list: List<Int>, res: Int): Int {
        return if (list.isNotEmpty()) {
            val a = list.first();
            recurse(list.drop(1), res * a + a)
        } else {
            res
        }
    }
    return recurse(list, 0)
}

fun call(list: List<Int>): Int {
    return list.fold(0) { res, el ->
        res * el + el
    }
}