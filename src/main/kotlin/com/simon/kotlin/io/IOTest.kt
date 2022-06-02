package com.simon.kotlin.io

import java.io.File

fun main() {
    val currentDir = "src/main/resources/"
    val file = File(currentDir, "KotlinTest.json")
    file.writeText("呵呵呵哈哈哈")
    println(file.readText())

    file.writeBytes(byteArrayOf(12, 56, 83, 57))
    println(file.readText())

    //追加方式写入字节或字符
    file.appendBytes(byteArrayOf(93, 85, 74, 93))
    file.appendText("吼啊")
    println(file.readText())

}