package com.simon.kotlin.coroutines.select

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select


data class Product(
    val productId: String,
    val price: Double,
    // 是不是缓存信息
    val isCache: Boolean = false,
)

fun main() = run {
    selectCase()
    selectWithChannel()
}

fun selectCase() = runBlocking {
    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): Product? {
        delay(200L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}==${product.price}")
    }

    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    // 1，缓存和网络，并发执行
    val cacheDeferred = async { getCacheInfo(productId) }
    val latestDeferred = async { getNetworkInfo(productId) }

    // 2，在缓存和网络中间，选择最快的结果
    val product = select<Product?> {
        cacheDeferred.onAwait {
            it?.copy(isCache = true)
        }

        latestDeferred.onAwait {
            it?.copy(isCache = false)
        }
    }

    // 3，更新UI
    if (product != null) {
        updateUI(product)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

    // 4，如果当前结果是缓存，那么再取最新的网络服务结果
    if (product != null && product.isCache) {
        val latest = latestDeferred.await() ?: return@runBlocking
        updateUI(latest)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }
}


fun selectWithChannel() = runBlocking {
    suspend fun <T> fastest(vararg deferreds: Deferred<T>): T = select {
        fun cancelAll() = deferreds.forEach { it.cancel() }

        for (deferred in deferreds) {
            deferred.onAwait {
                cancelAll()
                it
            }
        }
    }

    val deferred1 = async {
        delay(100L)
        println("done1")    // 没机会执行
        "result1"
    }

    val deferred2 = async {
        delay(50L)
        println("done2")
        "result2"
    }

    val deferred3 = async {
        delay(10000L)
        println("done3")    // 没机会执行
        "result3"
    }

    val deferred4 = async {
        delay(2000L)
        println("done4")    // 没机会执行
        "result4"
    }

    val deferred5 = async {
        delay(14000L)
        println("done5")    // 没机会执行
        "result5"
    }

    val result = fastest(deferred1, deferred2, deferred3, deferred4, deferred5)
    println(result)
}

