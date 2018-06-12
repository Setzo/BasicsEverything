package com.wpruszak.kotlin

import java.math.BigInteger


fun main(args: Array<String>) {
    println(factorial(BigInteger.TEN))
    println(tailRecursiveFactorial(BigInteger.TEN))
    // Stack overflow error
//    println(factorial(BigInteger.valueOf(10000)))
    println(tailRecursiveFactorial(BigInteger.valueOf(10000)))
}

tailrec fun tailRecursiveFactorial(n: BigInteger, c: BigInteger = BigInteger.ONE): BigInteger =
        if (n == BigInteger.ONE) c
        else tailRecursiveFactorial(n - BigInteger.ONE, c * n)

fun factorial(n: BigInteger): BigInteger =
        if (n == BigInteger.ONE) BigInteger.ONE
        else n * factorial(n - BigInteger.ONE)
