package com.wpruszak.kotlin

interface Interfaces {
    fun method()
}

open class Inherits(protected val test: String) : Interfaces {
    override fun method() = println("Works: $test.")
}

class InheritsChild(test: String, var test2: Int) : Inherits(test) {

    var property: String = "property"
    private set

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val inherits = Inherits("I'm working")
            inherits.method()
            val test = Test()
            test.method()
            InheritsChild("a", 2).method()
            InheritsChild("b", 3, "c")
            println(X("aa").name)
            println(X("aa").x)
            println(X(x = 43, name = "wtf").x)
            println(X(x = 43, name = "wtf").name)
            InheritsChild.staticTest()

            val instance = InheritsChild("a", 2)
            println(instance.property)
            instance.property = "a23"
            println(instance.property)
        }

        fun staticTest() {
            println("Static method.")
        }
    }

    constructor(test: String, test2: Int, x: String) : this(test, test2) {
        println(x)
    }

    init {
        println("init")
    }

    override fun method() {
        super.method()
        println("$test : $test2")
    }
}

open class X(val name: String, val x: Int = 2)
