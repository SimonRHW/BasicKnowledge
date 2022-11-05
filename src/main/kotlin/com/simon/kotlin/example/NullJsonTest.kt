package com.simon.kotlin.example

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simon.kotlin.bean.VolumeResponse


fun main() {
    val json = "{\"code\":\"547007\",\"description\":\"查询有效套餐以及流量信息失败\",\"data\":{}}"
    val gson = Gson()
    val test: VolumeResponse<List<String>> =
        gson.fromJson(
            json,
            object : TypeToken<VolumeResponse<List<String>>>() {}.type
        )
}