package com.wpruszak.kotlin

fun main(args: Array<String>) {
    val x = listOf(1, 2, 3, 4, 5)

    println(x)
    println(x.itemAt(4))

    println(isIt<Int>("efwwef"))
    println(isIt<Int>(4))
}

inline fun <reified T> isIt(value: Any) = value is T

fun <T> List<T>.itemAt(index: Int): T {
    return this[index]
}
