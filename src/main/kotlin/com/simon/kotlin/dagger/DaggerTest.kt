package com.simon.kotlin.dagger

import javax.inject.Inject

//@Inject constructor(private val apiServices: ApiServices)
class DaggerTest {

    @Inject
    lateinit var apiServices: ApiServices

    @Inject
    lateinit var apiTwoServices: ApiTwoServices

    @Inject
    lateinit var apiThreeServices: ApiThreeServices

    fun doSomeThing() {
        apiServices.doPrintln()

        apiTwoServices.doPrintln()

        apiThreeServices.doPrintln()
    }

}