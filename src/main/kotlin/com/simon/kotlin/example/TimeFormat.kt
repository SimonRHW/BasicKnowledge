

package com.simon.kotlin.example


import java.sql.Timestamp
import java.text.SimpleDateFormat

import java.util.*


fun main() {
    val expirationDate = "Jun 1, 2022 2:47:00 PM"
//    val expirationDate = "Jul 1, 2022 1:20:00 PM"
    formatTime(expirationDate)
}

fun formatTime(timeStr: String, targetFormat: String = "yyyy-MM-dd HH:mm:ss") {
    val simpleDateFormat = SimpleDateFormat(targetFormat, Locale.CHINA)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
    println("format " + simpleDateFormat.format(Date(timeStr)))
    val parsedDate: Date = simpleDateFormat.parse(simpleDateFormat.format(Date(timeStr)))
    val timestamp = Timestamp(parsedDate.time)
    println("Timestamp ${timestamp.time}")
    val currentTimeMillis = System.currentTimeMillis()
    println("currentTimeMillis $currentTimeMillis")
    println("30day " + time())
    println("时间差值 " + (timestamp.time - currentTimeMillis))
    println("时间差值是否超过30天 " + ((timestamp.time - currentTimeMillis) > time()))
}

fun time(): Long {
    return 30 * 24 * 60 * 60 * 1000L
}