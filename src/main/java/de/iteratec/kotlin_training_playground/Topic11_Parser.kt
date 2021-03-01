package de.iteratec.kotlin_training_playground

import java.io.File
import java.math.BigDecimal
import java.util.*

fun main() {
    val file = File("kunden.txt")

    // step 1: print file content
    printContent(file)

    // step 2: read file content as List<String>
    printLines(file)

    // step 3: look only for 'Kunde' lines and create a Customer class,
    //  with firstName, lastName, and gender
    val simpleCustomers = parseSimpleCustomers(file.readText())
    println(simpleCustomers)

    // step 4: write a test for that in Project10ParserTest
    //   tip: use a multiline string in test method :-)

    // step 5: use .chunked(6) to split original content into list of lines for each customer
    val chunks = readIntoCustomerChunks(file.readText())
    println("Got chunks for ${chunks.size} customers: $chunks")

    // step 6: parse everything into simple values :-)
    val customers = parseCustomers(file.readText())
    println(customers)

    // step 7: parse interests into typesafe set, like Set<Interest>
    val typeSafeCustomers = parseTypeSafeCustomers(file.readText())
    println(typeSafeCustomers)
}

fun printContent(file: File) {
    println(file.readText())
}

fun printLines(file: File) {
    println(file.readLines())
}

fun parseSimpleCustomers(content: String): List<SimpleCustomer> {
    return content.lines()
        .filter { it.startsWith("Kunde:") }
        .map { it.removePrefix("Kunde:") }
        .map { line ->
            val (lastName, firstName, gender) = line.split(',').map { it.trim() }
            SimpleCustomer(
                firstName = firstName,
                lastName = lastName,
                gender = Gender.values().first { it.genderName == gender }
            )
        }
}

data class SimpleCustomer(
    val firstName: String,
    val lastName: String,
    val gender: Gender
)

enum class Gender(val genderName: String) {
    Male("männlich"),
    Female("weiblich"),
    Diverse("divers")
}

fun readIntoCustomerChunks(content: String): List<List<String>> {
    return content.lines().chunked(6) { chunk ->
        chunk.filter { line: String -> line.isNotEmpty() }
    }
}

data class Customer(
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Int,
    val creditCard: String,
    val balance: String,
    val interests: String
)

fun parseCustomers(content: String): List<Customer> {
    return readIntoCustomerChunks(content).map { chunk ->
        val (nameLine, ageLine, creditCardLine, balanceLine, interestsLine) = chunk

        val (lastName, firstName, gender) = nameLine
            .removePrefix("Kunde:")
            .trim()
            .split(',')
            .map { it.trim() }

        val age = ageLine.removeSurrounding("Alter:", "Jahre").trim().toInt()

        val creditCard = creditCardLine.removePrefix("Kreditkarte:").trim()

        val balance = balanceLine.removePrefix("Kontostand:").trim()

        val interests: String = interestsLine.removePrefix("Interessen:").trim()

        Customer(
            firstName = firstName,
            lastName = lastName,
            gender = Gender.values().first { it.genderName == gender },
            age = age,
            creditCard = creditCard,
            balance = balance,
            interests = interests
        )
    }
}

fun parseTypeSafeCustomers(content: String): List<TypeSafeCustomer> {
    // we could have copied the parsing logic here again - but where is the fun in that? ;-)
    return parseCustomers(content).map { customer ->
        val (cardNumber, cardIssuer) = customer.creditCard.split(',').map { it.trim() }
        val (amount, currencyCode) = customer.balance.split(' ')
        val interests = customer.interests
            .split(',').map { it.trim() } // split by comma and remove whitespace
            .filter { it.isNotEmpty() } // only take non-empty interests
            .map { interest -> Interest.values().first { interest == it.name } } // map to enum
            .toSet()

        TypeSafeCustomer(
            customer.firstName,
            customer.lastName,
            customer.gender,
            customer.age,
            CreditCard(cardNumber, cardIssuer),
            Balance(BigDecimal(amount.replace(',', '.')), Currency.getInstance(currencyCode)),
            interests
        )
    }
}

data class TypeSafeCustomer(
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Int,
    val creditCard: CreditCard,
    val balance: Balance,
    val interests: Set<Interest>
)

data class CreditCard(val number: String, val issuer: String)

data class Balance(val amount: BigDecimal, val currency: Currency)

enum class Interest {
    Autos, Eisenbahnen, Tennis, Kochen, Programmieren, Aktien, Geld, Reichtum
}
