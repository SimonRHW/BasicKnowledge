package com.simon.kotlin.bean

data class VolumeResponse<T>(
        val code: Int,
        val description: String,
        var data: T?
) {
    fun isSuccess(): Boolean {
        return code == 0
    }
}