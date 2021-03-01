package de.iteratec.kotlin_training_playground

import de.iteratec.kotlin_training_playground.CreateUserResult.*
import java.util.*
import java.util.UUID.randomUUID

data class UserCredentials(
    val username: String,
    val password: String
)

data class User(
    val id: UUID = randomUUID(),
    val username: String
)

sealed class CreateUserResult {
    class Success(val user: User) : CreateUserResult()
    class OtherError(val message: String) : CreateUserResult()
    class UserDoesAlreadyExist(val username: String) : CreateUserResult()
}

/**
 * Creates the specified user in the database.
 */
fun createUser(credentials: UserCredentials): CreateUserResult {
    // TODO lol, didn't implement the database part

    if (credentials.username.isEmpty()) {
        return OtherError("username is empty")
    }
    return Success(User(username = credentials.username))
}


fun main() {
    val credentials = UserCredentials(
        username = "marc",
        password = "very secure"
    )

    val result = createUser(credentials)
    val message = when (result) {
        is Success -> "User ${result.user.username} created"
        is OtherError -> "Other error occurred: ${result.message}"
        is UserDoesAlreadyExist -> "Error: user ${result.username} does already exist"
    }
    println(message)

    task1()
    task2()
}

/**
 * Task 1
 * Replace the if statements with a single when statement.
 */
private fun task1() {
    println("#### Task 1")

    val credentials = UserCredentials(username = "bob", password = "narwhal123")
    val createUserResult = createUser(credentials)
    val message = when (createUserResult) {
        is Success -> "User ${createUserResult.user.username} has been created!"
        is OtherError -> "User could not be created."
        else -> "Unknown result"
    }

    println(message)
}

/**
 * Task 2
 * Replace the if statements with a single when statement. Make 'passwordStrength' immutable and assign is to
 * the value of the 'when' expression.
 */
private fun task2() {
    println("#### Task 2")

    val password = "narwhal123"
    val passwordStrength = when (password.length) {
        in 0..3 -> "weak"
        in 4..12 -> "medium"
        else -> "strong"
    }

    println("Password $password has strength '$passwordStrength'")
}
