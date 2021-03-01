package de.iteratec.kotlin_training_playground

data class Dog(
    val name: String = "dog",
    val age: Int = 1
)

fun main() {
    val dog = Dog(name = "Kessi")
    println("dog = $dog")

    task1()
    task2()
    task3()
}

/**
 * Task 1
 * Create a class 'Horse' with props 'name' (String) and 'weight' (Int) defined in the constructor.
 * Add a default value 500 for the prop 'weight'.
 * Then create an instance of a Horse and print both properties.
 */
private fun task1() {
    println("#### Task 1")

    class Horse(val name: String, val weight: Int = 500)
    val horse = Horse(name = "Stefan")
    println("name = ${horse.name}, weight = ${horse.weight}")
}

/**
 * Task 2
 * Run the code and read the output. Then turn 'Horse' into a data class and check how the output changes.
 */
private fun task2() {
    println("#### Task 2")

    data class Horse(val name: String, val age: Int)

    val horseAlbrecht = Horse("Albrecht", 8)
    val horseAlbrecht2 = Horse("Albrecht", 8)
    println(horseAlbrecht == horseAlbrecht2) // After switching to data class, instances are equal
    println(horseAlbrecht) // ...and toString() gets implemented for you
}

/**
 * Task 3
 * Use the copy function to create another instance of Lucy with a sound 'Pur pur'.
 * All other properties should remain unchanged.
 */
private fun task3() {
    println("#### Task 3")

    data class Cat(val name: String, val age: Int, val sound: String, val isHungry: Boolean)
    val lucy = Cat(name = "Lucy", age = 4, sound = "Meow meow", isHungry = false)
    val otherLucy = lucy.copy(sound = "Pur pur")
    println(lucy)
    println(otherLucy)
}