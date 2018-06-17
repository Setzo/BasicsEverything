package com.wpruszak.kotlin

class Nullable(var x: String?)
class NonNull(var x: String)

fun nonNull(x: String) {
    println(x)
}

@Suppress("CAST_NEVER_SUCCEEDS", "UNREACHABLE_CODE", "UNNECESSARY_SAFE_CALL", "ALWAYS_NULL")
fun main(args: Array<String>) {

    var x: Nullable? = Nullable(null)
//    val y = NonNull(null) // compile-time error
    val y = NonNull("a")

    val z: String? = null
    println(z?.length)
    println(z?.hashCode())

    println()
    println(x)
    println(x ?: Nullable("efw"))
    x = null
    println(x ?: "x was null")

    val c = y as? Comparable<Int>
    println(c)
    val d = y as? Any
    println(d)

    null?.let { nonNull(it) }
    "3423453"?.let { nonNull(it) }

    println(x!!.hashCode())
}
