package com.simon.kotlin.example

import java.math.BigDecimal
import java.math.MathContext

fun main(){
    val num1 = BigDecimal("96")
    val num2 = BigDecimal("296")
    val result = num1.divide(num2, MathContext.DECIMAL128)
    print(result)
}

