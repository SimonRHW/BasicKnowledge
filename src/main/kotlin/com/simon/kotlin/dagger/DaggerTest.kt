package com.simon.kotlin.dagger

import javax.inject.Inject

class DaggerTest {

    @Inject
    lateinit var apiServices: ApiServices

    @Inject
    lateinit var apiTwoServices: ApiTwoServices

    @Inject
    lateinit var apiThreeServices: ApiThreeServices

    fun doSomeThing() {
        println("doSomeThing")
//        apiServices.doPrintln()
//
//        apiTwoServices.doPrintln()
//
//        apiThreeServices.doPrintln()
    }

}