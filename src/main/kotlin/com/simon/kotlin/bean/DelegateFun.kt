package com.simon.kotlin.bean

import com.simon.kotlin.example.printlnClassName

/**
 * @author Simon
 * @date 2020/2/27 15:22
 * Desc ：
 */

interface CanFly {
    fun fly()
}

interface CanEat {
    fun eat()
}

open class Flyer : CanFly {
    override fun fly() {
        println("flyer:i can fly")
    }
}

open class Animal : CanEat {
    override fun eat() {
        println("animal:i can eat")
    }
}

class Bird(flyer: Flyer, animal: Animal) : CanFly by flyer, CanEat by animal

fun main() {

    val flyer = Flyer()
    val animal = Animal()
    val bird = Bird(flyer, animal)
    bird.fly()
    bird.eat()

    val pair = Pair(20.1, 1)
    val data1 = pair.first
    val data2 = pair.second
    printlnClassName(data1)
    printlnClassName(data2)
    val (data3,data4)= Pair("hehe",100)
    printlnClassName(data3)
    printlnClassName(data4)
}