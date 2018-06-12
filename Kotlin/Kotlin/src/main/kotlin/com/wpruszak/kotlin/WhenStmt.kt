package com.wpruszak.kotlin

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val z = 2

    val a = when (z) {
        1 -> "one"
        2 -> "two"
        else -> exitProcess(3)
    }

    println(a)
}
