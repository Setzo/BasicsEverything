package com.wpruszak.kotlin

fun main(args: Array<String>) {
    val x = (1..10).toList()
    println(x)
    val y = x.filter { it > 5 }.map { it - 10 }
    println(y)

    val lessThanThree = { it: Int -> it < 3 }

    println(x.all(lessThanThree))
    println(x.any(lessThanThree))
    println(x.count(lessThanThree))
    println(x.all { it > -100 })

    // asSequence is lazy, by default map / filter will produce a list (eagerly).
    val toFlatten = (1..10).asSequence().map { ((it - 1) * 10 + 1)..(it * 10) }.map { it.toList() }.toList()
    println(toFlatten)
    val flattened = toFlatten.flatMap { it }
    println(flattened)

    // SAM - single abstract method (functional)
    val samConstruct = SAMInterface { println("something: $it") }
    samConstruct.doSomething(34)
}
