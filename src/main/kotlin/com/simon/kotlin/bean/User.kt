package com.simon.kotlin.bean

/**
 * @author Simon
 * Desc ：
 */

class User(
    val name: String = "simon",
    val age: Int = 18,
    val email: String = "simon@mail"
) {
    val category: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        if (age >= 18) "adult" else "boy"
    }
//    val category: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
//        if (age >= 18) "adult" else "boy"
//    }
//    val category: String by lazy(LazyThreadSafetyMode.NONE) {
//        if (age >= 18) "adult" else "boy"
//    }
}
//class User(
//    name: String = "simon",
//    age: Int = 18,
//    email: String = "simon@mail"
//) {
//    val name: String
//    val age: Int
//    val email: String
//    init {
//        println("do some other things")
//        this.name = name
//        this.age = age
//        this.email = email
//    }
//}
