package com.wpruszak.kotlin

fun main(args: Array<String>) {
    val x = "  this   \t\nis whitespace  \t"
    println(x.removeWhitespace())
    println(removeWhitespace2(x))
}

fun String.removeWhitespace(): String {
    return Regex("\\s+").replace(this, "")
}

fun removeWhitespace2(value: String): String {
    return Regex("\\s+").replace(value, "")
}
