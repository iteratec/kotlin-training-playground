package de.iteratec.kotlin_training_playground

data class Member(val firstName: String, val lastName: String)

fun main() {
    val abc = listOf('a', 'b', 'c')

    val members = listOf(
        Member("Pablo", "Roman"),
        Member("Rachael", "Piper"),
        Member("Jonas", "Terrell"),
        Member("Mary", "Travis"),
        Member("Christian", "Findlay")
    )

    val sortedByLastName = members.sortedBy { it.lastName }

    val fullNames = members.map { "${it.firstName} ${it.lastName}" }

    val countLastNameStartWithT = members
        .filter { it.lastName.startsWith("T") }
        .count()


    // this will not compile because lists are unmodifiable by default
//    members.add(Member("First", "Last"))
    val modifiableMembers = members.toMutableList()

    // this works:
    modifiableMembers.add(Member("First", "Last"))


    // to use similar to Java streams (lazy, not eager), use asSequence():
    val countLastNameStartWithTButAsSequence = members
        .asSequence()
        .filter { it.lastName.startsWith("T") }
        .count()


    // long example with groupBy, map, toMap, and forEach
    members
        .sortedBy { it.firstName }
        .filterNot { it.lastName == "Hacker" }
        .groupBy { it.lastName.first() }
        .map { (letter, members) ->
            letter to members.map { "${it.firstName} ${it.lastName}" }
        }
        .toMap()
        .forEach { (letter, membersStartingWithLetter) ->
            println("$letter -> $membersStartingWithLetter")
        }


    // immutable maps by default, and infix 'to' makes maps so much easier to read
    val map = mapOf(
        "key1" to "value",
        "key2" to "value",
        "key3" to "value"
    )
}