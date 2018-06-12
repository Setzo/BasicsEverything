package com.wpruszak.kotlin

import java.util.Arrays
import java.util.TreeMap

fun main(args: Array<String>) {
    val z = Arrays.asList("a", "b", "c", "d")

    for ((index, element) in z.withIndex()) {
        println("$index: $element")
    }

    val map = TreeMap<String, Int>()
    map["something"] = 30
    map["3something"] = 30345
    map["1something"] = 3530
    map["2something"] = 301

    for ((str, int) in map) {
        println("$str: $int")
    }
}
