package de.iteratec.kotlin_training_playground

import java.util.*
import kotlin.random.Random

fun main() {
    // this is not possible :-)
    //    val c : String = null

    // this can be null
    val d: String? = null

    println(
        """
        d = $d
        """.trimIndent()
    )

    task1()
    task2()
}

/**
 * Task 1
 * Rewrite the code using the safe call operator ?. and Elvis operator ?:
 */
private fun task1() {
    println("#### Task 1")

    val dogName: String? = if (Random.nextBoolean()) "Rocky" else null
    println(dogName?.first() ?: "")
}

/**
 * Task 2
 * Uncomment the code & see what happens 😉 (then: how do we fix this?)
 */
private fun task2() {
    println("#### Task 2")

    val scanner = Scanner(">> only Rocky is here <<")
    val lucy: String? = scanner.findInLine("lucy") // Explicitly specify the type when using platform types
    val firstLetter = lucy?.first() ?: "no first letter" // Safe call need to be added, because lucy is nullable
    println(firstLetter)
}