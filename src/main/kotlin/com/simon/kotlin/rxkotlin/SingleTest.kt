package com.simon.kotlin.rxkotlin

import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun main() {
    Single.create { emitter: SingleEmitter<String> ->
        println(Thread.currentThread())
        emitter.onSuccess("test")
    }.subscribeOn(Schedulers.io())
        .delay(1, TimeUnit.SECONDS)
        .observeOn(Schedulers.trampoline())
        .timeout(3, TimeUnit.SECONDS, Single.error(Exception("")))
        .subscribeOn(Schedulers.io())
        .subscribe({
            println(it + Thread.currentThread())
        }, {

        })
}