package com.simon.kotlin.example

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody


/**
 * @author Ren.Hong wei
 * @Description
 */
fun main() {
    val client = OkHttpClient()

    val request: Request = Request.Builder()
        .url("https://restapi.amap.com/v3/geocode/regeo?key=ad6eef7e6878ef4ccd89a0633d687ccb&location=121.548986,31.220355")
        .build()
    try {
        val response: Response = client.newCall(request).execute()
        val responseBody: ResponseBody? = response.body
        val responseData = responseBody?.string()
        val jsonObject: JsonObject = JsonParser.parseString(responseData).asJsonObject
        val regeocodeComponent = jsonObject["regeocode"].asJsonObject
        val addressComponent = regeocodeComponent["addressComponent"].asJsonObject
        val adcode = addressComponent["adcode"]
        if (adcode.toString().length > 5) {
            println(adcode)
        } else {
            println("无效")
        }
        println(responseData)
        response.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}