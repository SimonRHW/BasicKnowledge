package com.simon.kotlin.example

import java.text.SimpleDateFormat
import java.util.*


fun main() {
    formatTime()
}

fun formatTime(timeStr: String = "Jul 9, 2023 3:20:00 PM", targetFormat: String = "dd-MM-yyyy") {
    val simpleDateFormat = SimpleDateFormat(targetFormat, Locale.CHINA)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+08:00")
    println(simpleDateFormat.format(Date(timeStr)))
}