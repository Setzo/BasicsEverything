package com.wpruszak.kotlin

object Xa : Comparator<Int> {
    override fun compare(o1: Int?, o2: Int?): Int {
        return 1
    }
}

data class Person(var username: String, var something: String, var x: Int)

fun <T, R> myWith(receiver: T, function: T.() -> R): R {
    return receiver.function()
}

fun <T> myApply(receiver: T, function: T.() -> Unit): T {
    receiver.function()
    return receiver
}

fun main(args: Array<String>) {
    val p = Person("a", "b", 2)
    println(p)
    myWith(p) {
        username = "frdeer"
        x = 32
    }
    println(p)
    myApply(p) {
        something = "123"
    }
    println(p)
    p.apply { something = "231A" }
    println(p)
    with(p) {
        username = "v"
        x = 1
        something = "3"
    }
    println(p)
    println(myWith(p) {
        344223234
    })
}
