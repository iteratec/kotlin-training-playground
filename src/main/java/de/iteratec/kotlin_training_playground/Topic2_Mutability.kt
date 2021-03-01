package de.iteratec.kotlin_training_playground

fun main() {
    val a = "This is a non-null string with implicit type"
    val b: String = "This is a non-null string with explicit type"

    // all these variables are final, so this won't work:
    //    a = ""
    //    b = ""

    // this is a mutable variable:
    var e = ""
    e = "this can be mutated"


    println(
        """
        a = $a
        b = $b
        e = $e
        """.trimIndent()
    )

    task1()
    task2()
    task3()
}

/**
 * Task 1
 * Uncomment the line and fix the code, so it prints 'Lucy'
 */
private fun task1() {
    println("#### Task 1")

    var catName = "Lisa" // var instead of val must be used
    catName = "Lucy"
    println(catName)
}

/**
 * Task 2
 * Uncomment the line and fix the code, so it prints 'Lucy'
 */
private fun task2() {
    println("#### Task 2")

    var catName = "Katy Purry" // inferred type of catName (Int) was wrong
    catName = "Lucy"
    println(catName)
}

/**
 * Task 3
 * Uncomment the line and fix the code, so it prints 'Lucy'
 */
private fun task3() {
    println("#### Task 3")

    var catName: String? = null // assigning null infers the type Nothing?, use explicit type declaration instead
    catName = "Lucy"
    println(catName)
}