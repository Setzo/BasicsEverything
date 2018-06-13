package com.wpruszak.kotlin

typealias Mylist = List<List<List<List<Int>>>>

fun main(args: Array<String>) {
    println('a')
    val list = listOf(1, 2, 3, 4, 5)
    testInliningReturnInInlinedScope(list)
    testInliningReturnInFunctionScope(list)

    val llist = listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7), listOf(8, 9, 0))
    testInliningReturn(llist)
    test()

    val x: Mylist = listOf(listOf(listOf(listOf(1, 2, 3))))
    println(x)

    val lazya by lazy {
        println("woke up")
        return@lazy 2
    }
    val y by lazy {
        println("y woke up")
        lazya
    }
    println("sleeping")

    println(y)

    val zz = "some string".takeUnless(String::isBlank) ?: "was blank"
    val zx = "".takeUnless(String::isBlank) ?: "was blank"
    println(zz)
    println(zx)
}

fun test(): Int {
    add(1, 2) // Does not return
    println("test")
    return 2
}

inline fun add(a: Int, b: Int): Int {
    return a + b
}

fun testInliningReturnInFunctionScope(arg: List<Int>) {
    println("Returning from function scope - start.")
    arg.forEach { x ->
        println(x)
        return
    }
    println("Returning from function scope - end.")
}

fun testInliningReturnInInlinedScope(arg: List<Int>) {
    println("Returning from foreach scope - start.")
    arg.forEach { x ->
        println(x)
        return@forEach
    }
    println("Returning from foreach scope - end.")
}

fun testInliningReturn(arg: List<List<Int>>) {
    arg.forEach outerForeach@{ list ->
        list.forEach { element ->
            print(element)
            print(", ")
            return@outerForeach
        }
    }
    println()
}
