package com.wpruszak.kotlin

sealed class Test2

sealed class T2 : Test2()

class T3 : T2()

//class T4: T3() // compilation error

fun main(args: Array<String>) {
    something1(1)
    something2(3)
    something3(34)
    var a: (Int) -> String = Int::toString
}

fun something1(x: Int): String = if (x == 1) "one" else "two"
fun something2(x: Number): String = when (x) {
    1 -> "one"
    is Int -> "int"
    else -> "else"
}

fun something3(x: Int): String = if (x == 1) {
    something1(x)
} else {
    something2(x)
}
