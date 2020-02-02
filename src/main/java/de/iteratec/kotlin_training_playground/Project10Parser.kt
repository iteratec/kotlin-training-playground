package de.iteratec.kotlin_training_playground

import java.io.File

fun main() {
    val file = File("project10parseme.txt")

    // step 1: print file content
    printContent(file)

    // step 2: read file content as List<String>
    printLines(file)

    // step 3: look only for 'Kunde' lines and create a Customer class,
    //  with firstName, lastName, and gender
    val customers = parseCustomers(file.readText())
    println(customers)

    // step 4: write a test for that in Project10ParserTest
    //   tip: use a multiline string in test method :-)

    // step 5: use .chunked(6) to split original content into list of lines for each customer
    // step 6: parse everything into simple values :-)
    // step 7: parse interests into typesafe set, like Set<Interest>
}

fun printContent(file: File) {
    println(file.readText())
}

fun printLines(file: File) {
    println(file.readLines())
}

fun parseCustomers(content: String): List<Customer> {
    return content.lines()
        .filter { it.startsWith("Kunde:") }
        .map { it.removePrefix("Kunde:") }
        .map { line ->
            val (lastName, firstName, gender) = line.split(',').map { it.trim() }
            Customer(
                firstName = firstName,
                lastName = lastName,
                gender = Gender.values().first { it.genderName == gender }
            )
        }
}

data class Customer(
    val firstName: String,
    val lastName: String,
    val gender: Gender
)

enum class Gender(val genderName: String) {
    Male("männlich"),
    Female("weiblich"),
    Diverse("divers")
}