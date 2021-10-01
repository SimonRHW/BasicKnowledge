package com.simon.kotlin.properties

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 假设我们想要一个 String 属性参数，它总是有一个修剪过的字符串,可以按照以下这样做
 */
class Example {
    //https://kotlinlang.org/docs/properties.html
    var paramProperties: String = "  init  "
        set(value) {
            field = value.replace(" ", "")
        }
        get() {
            return field.trim()
        }

    //https://kotlinlang.org/docs/delegated-properties.html
    var paramDelegate: String by TrimDelegate()

    /*paramDelegate is equivalent to this
    private val delegate = TrimDelegate()
    var param: String
        get() = delegate.getValue(this, ::param)
        set(value) {
            delegate.setValue(this, ::param, value)
        }*/
}

class TrimDelegate : ReadWriteProperty<Any?, String> {

    private var trimmedValue: String = " TrimDelegate "

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): String {
        return trimmedValue
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>, value: String
    ) {
        trimmedValue = value.trim()
    }
}

fun main() {
    val e = Example()
    println(e.paramProperties)
    println(e.paramDelegate)
    e.paramProperties = " param Properties "
    e.paramDelegate = " paramDelegate "
    println(e.paramProperties)
    println(e.paramDelegate)
}

