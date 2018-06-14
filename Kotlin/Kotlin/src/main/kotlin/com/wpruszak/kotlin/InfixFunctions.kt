package com.wpruszak.kotlin

class Something(val x: Int, val y: Int) {
    infix fun plus(a: Something): Int {
        return x + y + a.x + a.y
    }

    infix operator fun times(a: Something): Int {
        return x * y * a.x * a.y
    }
}

abstract class Wtf {
    inline fun <reified T> test(crossinline map: (Int) -> T, noinline str: (T) -> String): (Int) -> String = { str(map(it)) }

    internal abstract tailrec suspend infix operator fun plus(other: Wtf): (Int, Int) -> Int
}

fun vararg(x: Int, vararg c: Int) {
    println(x)
    println(c.joinToString())
}

fun t() {
    t1@ fun t() {
        t2@ fun t() {
            println("t")
        }
    }
}

fun main(args: Array<String>) {
    val x = Something(1, 2)
    val y = Something(3, 4)
    println(x plus y)
    println(x minus y)
    println(x * y)
    vararg(2, 3, 4, 5, 6)
    t()
}

infix fun Something.minus(a: Something): Int {
    return x + y - a.x - a.y
}
