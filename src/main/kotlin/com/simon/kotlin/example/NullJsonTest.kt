package com.simon.kotlin.example

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simon.kotlin.bean.VolumeResponse
import com.simon.kotlin.example.NullJsonTest.Companion.json

class NullJsonTest {

    companion object{
        val  json = "{\"code\":\"547007\",\"description\":\"查询有效套餐以及流量信息失败\",\"data\":{}}"
    }

}

fun main(){
    val gson = Gson()
    val test:VolumeResponse<List<String>> =
        gson.fromJson(
            json,
            object : TypeToken<VolumeResponse<List<String>>>() {}.type
        )
}