package com.simon.kotlin.bean

/**
 * @author Simon
 * Desc ：骡子  内部类与嵌套类的差异
 */
class Mule {

    val name: String = "put  class "
    fun runFast() {
        HorseC().runFast()
    }

    fun doLongTimeThing() {
        DonkeyC().doLongTimeThing()
    }

    private inner class HorseC : Horse()

    private inner class DonkeyC : Donkey()

    class TestClass {
        fun printName() {
            //print("$name") 嵌套类 Unresolved reference: name
            print("test")
        }
    }

}