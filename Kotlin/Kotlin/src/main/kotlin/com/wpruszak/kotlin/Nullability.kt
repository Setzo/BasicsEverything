package com.wpruszak.kotlin

class Nullable(var x: String?)
class NonNull(var x: String)

fun main(args: Array<String>) {

    val x = Nullable(null)
//    val y = NonNull(null) // compile-time error
    val y = NonNull("a")

    var z: String? = null
    println(z?.length)
    println(z?.hashCode())
}
