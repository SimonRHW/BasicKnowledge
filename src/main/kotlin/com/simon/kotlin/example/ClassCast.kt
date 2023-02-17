package com.simon.kotlin.example

/**
 * @author Simon
 * @desc
 */
class ClassCast<T> {
    private val data = arrayListOf<T>()
    fun setData(newData: List<T>) {
        data.clear()
        data.addAll(newData)
    }

    fun getData(position: Int): T? {
        if (position < 0 || position > data.size - 1) {
            return null as T
        }
        return data[position]
    }
}

fun main() {
    val temp = ClassCast<String>()
    temp.setData(arrayListOf("0", "1"))
    val data = temp.getData(-1)
    println(data?.javaClass?.name)
}