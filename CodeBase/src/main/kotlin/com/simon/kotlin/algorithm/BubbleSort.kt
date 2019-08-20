package com.simon.kotlin.algorithm

/**
 * @author Simon
 */
fun bubbleSort(data: Array<Int>) {

    val arrayLength = data.size

    for (i in 0 until arrayLength) {
        for (j in 0 until arrayLength - i - 1) {
            if (data[j] > data[j + 1]) {
                val temp = data[j]
                data[j] = data[j + 1]
                data[j + 1] = temp

            }
        }
    }

}