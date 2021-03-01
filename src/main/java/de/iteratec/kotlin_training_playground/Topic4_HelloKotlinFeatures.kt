package de.iteratec.kotlin_training_playground

// this example is inspired by the great book 'Kotlin in Action'
//  more info: https://www.manning.com/books/kotlin-in-action

data class Person( // data class
    val firstName: String,
    val age: Int? = null // nullable Int, default value
)

fun main() { // top-level function
    val people = listOf( // value, immutable list, type inference
        Person("Max"),
        Person("Barbara", age = 32), // named argument
        Person("Thomas", 28)
    )

    val oldest = people.maxBy { it.age ?: 0 } // collection functions, elvis operator, lambda expression
    val youngest = people.filter { it.age != null }.minBy { it.age!! } // double bang operator (use with care)

    println("oldest is $oldest, youngest is $youngest") // string template, auto-generated toString from data class
}
