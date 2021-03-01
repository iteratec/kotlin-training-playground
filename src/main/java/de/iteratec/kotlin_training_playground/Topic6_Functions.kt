package de.iteratec.kotlin_training_playground

import java.util.*


fun String.toUUID(): UUID = UUID.fromString(this)

fun Int?.doubleIt() = if (this == null) null else this * 2

fun invokeAndPrint(operation: (a: Int, b: Int) -> Int, first: Int, second: Int) {
    println(operation(first, second))
}

fun add(a: Int, b: Int = 0): Int {
    //println("Adding $a and $b")
    return a + b
}

fun subtract(a: Int, b: Int): Int {
    return a - b
}

fun printLazy(lazyMessage: () -> String) {
    println(lazyMessage())
}

fun main() {
    // regular invocation of a method (with a default param)
    val sumOf2AndDefaultParam = add(2)
    println("sum of 2 and defaultParam is $sumOf2AndDefaultParam")
    // methods can also be directly invoked in a template String
    println("sum of 2 and 4 is ${add(2, 4)} in a template string")


    val uuid = "ff820e1b-312f-4e48-94b0-967f3808dc8c".toUUID()
    println(uuid)
    val doubled = 2.doubleIt()
    println("2 doubled is $doubled")


    // pass a method reference
    invokeAndPrint(::subtract, 100, 2)
    // pass a lambda
    invokeAndPrint({ a, b -> a * b }, 4, 4)
    // pass anonymous function
    invokeAndPrint(fun(a: Int, b: Int): Int {
        return a / b
    }, 10, 2)


    // pass an anonymous function that provides a string 'lazily'
    printLazy(fun(): String {
        return "sum of 123 and 321 is ${add(123, 321)}"
    })
    // or use a lambda
    // - as the lambda is the last param, it can be moved out of the 'printLazy(  )' parentheses
    // - as the lambda is actually the only param, we can even remove those parentheses
    printLazy {
        "sum of 123 and 321 is ${add(123, 321)}"
    }
    // try whether 'add' wil be called, if inside printLazy the lazyMessage()-call is *not* invoked

    task1()
    task2()
    task3()
}


/**
 * Task 1
 * Uncomment the lines and implement the feed() function, so that the output is:
 * 'cat was fed with fish'
 * 'cat was fed with meat'
 */
private fun task1() {
    println("#### Task 1")

    fun feed(animal: String, food: String = "meat") {
        println("$animal was fed with $food")
    }

    feed(animal = "cat", food = "fish")
    feed(animal = "cat")
}

/**
 * Task 2
 * Implement a lambda function, that calculates the BMI of a cat (weight / (height*height)).
 * Then call the lambda and print the BMI of Lucy. Check the inferred type of the 'bmi' variable (Cmd + Mouseover (Mac) or Ctrl + Mouseover (Windows)).
 */
private fun task2() {
    println("#### Task 2")

    class Cat(val name: String, val weight: Double, val height: Double)

    val bmi = { weight: Double, height: Double -> weight / (height * height) }
    val alternativeBmi = { cat: Cat -> cat.weight / (cat.height * cat.height) }
    val lucy = Cat(name = "Lucy", weight = 8.0, height = 0.3)
    println("bmi of ${lucy.name} is ${bmi(lucy.weight, lucy.height)}")
    println("bmi of ${lucy.name} is ${alternativeBmi(lucy)}")
}

/**
 * Task 3
 * Uncomment the lines and implement an extension function isCatSound() on String,
 * that returns true when the string is "meow" or "pur" and false otherwise.
 */
private fun task3() {
    println("#### Task 3")

    fun String.isCatSound(): Boolean = this == "meow" || this == "pur"

    // or using the `in` keyword to find if it is contained in a collection/iterable:
    fun String.isCatSound2(): Boolean = this in listOf("meow", "pur")

    println("meow".isCatSound())
    println("pur".isCatSound())
    println("woof".isCatSound())
}
