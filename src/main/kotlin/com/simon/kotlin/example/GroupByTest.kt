package com.simon.kotlin.example

fun main() {
    val mList = arrayListOf(1, 0, 2, 3, 4, 5, 6)
    val groupByList = mList.groupBy {
        //定义key
        if (it % 2 == 0) {
            "偶数"
        } else {
            "奇数"
        }
    }
    println(groupByList)
    //这里返回两个元素的map
    //{偶数=[0, 2, 4, 6], 奇数=[1, 3, 5]}
    groupByList.entries.forEach {
        println("${it.key}==========${it.value}")
    }
    //偶数==========[0, 2, 4, 6]
    //奇数==========[1, 3, 5]
}