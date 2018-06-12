package com.wpruszak.kotlin

data class User(val name: String, val id: Int, val age: Int)

fun main(args: Array<String>) {
    val x = User("Mike", 21342, 32)
    println(x)

    val (name, id) = x
    println(name)
    println(id)

    val y = User("a", 2, 3)
    val (n, i, a) = y
    println(n)
    println(i)
    println(a)

    val yCopy = y.copy(age = 32332)
    print(yCopy)
}
