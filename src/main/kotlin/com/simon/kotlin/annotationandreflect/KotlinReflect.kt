package com.simon.kotlin.annotationandreflect

import kotlin.reflect.full.memberProperties
import kotlin.reflect.KMutableProperty1


fun main() {
    val student = Student("Tom", 99.5, 170)
    val school = School("PKU", "Beijing")
    readMembers(student)
//    readMembers(school)
//
//    modifyAddressMember(school)
//
//    readMembers(school)
//    readMembers(student)
}


fun readMembers(obj: Any) {
    println("${obj::class.simpleName}.${obj::class.annotations}")
    println("${obj::class.simpleName}.${obj::class.constructors}")
    obj::class.memberProperties.forEach {
        println("${obj::class.simpleName}.${it.name}=${it.getter.call(obj)}")
        println("${obj::class.simpleName}.${it.name}.${it.returnType}")
        println("${obj::class.simpleName}.${it.name}.${it.parameters}")
        println("${obj::class.simpleName}.${it.name}.${it.annotations}")
    }
}

@Deprecated("Test")
data class Student(
    val name: String,
    val score: Double,
    val height: Int
)

data class School(
    val name: String,
    var address: String
)


fun modifyAddressMember(obj: Any) {
    obj::class.memberProperties.forEach {
        if (it.name == "address" &&
            it is KMutableProperty1 &&
            it.setter.parameters.size == 2 &&
            it.getter.returnType.classifier == String::class
        ) {
            it.setter.call(obj, "China")
            println("====Address changed.====")
        }
    }
}

