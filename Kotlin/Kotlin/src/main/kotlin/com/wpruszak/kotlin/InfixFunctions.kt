package com.wpruszak.kotlin

class Something(val x: Int, val y: Int) {
    infix fun plus(a: Something): Int {
        return x + y + a.x + a.y
    }

    infix operator fun times(a: Something): Int {
        return x * y * a.x * a.y
    }
}

fun main(args: Array<String>) {
    val x = Something(1, 2)
    val y = Something(3, 4)
    println(x plus y)
    println(x minus y)
    println(x * y)
}

infix fun Something.minus(a: Something): Int {
    return x + y - a.x - a.y
}
