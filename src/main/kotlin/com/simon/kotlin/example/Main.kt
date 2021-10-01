package com.simon.kotlin.example

import com.simon.java.bean.User


/**
 * @author Simon
 * Desc ：验证入口
 */

fun main() {
    println(checkAppAddTime("test"))
    Thread.sleep(500)
    println(checkAppAddTime("test"))
    Thread.sleep(1500)
    println(checkAppAddTime("test"))
    val childrenAppList = listOf(User("1", 1, "haha"), User("2", 2, "heihei"), User("3", 3))
    println(childrenAppList.maxWithOrNull(compareBy { it.email }))

    val pair = Pair(20.1, 1)
    val data1 = pair.first
    val data2 = pair.second
    printlnClassName(data1)
    printlnClassName(data2)
    val (data3,data4)= Pair("hehe",100)
    printlnClassName(data3)
    printlnClassName(data4)
}

val appAddTimeCacheMap = hashMapOf<String, Long>()

fun checkAppAddTime(packageName: String): Boolean {
    return if (appAddTimeCacheMap.containsKey(packageName)) {
        System.currentTimeMillis() - appAddTimeCacheMap[packageName]!! > 1200
    } else {
        appAddTimeCacheMap[packageName] = System.currentTimeMillis()
        true
    }
}



