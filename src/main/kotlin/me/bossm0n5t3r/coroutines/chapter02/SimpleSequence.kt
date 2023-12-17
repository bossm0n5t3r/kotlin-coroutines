package me.bossm0n5t3r.coroutines.chapter02

private val seq =
    sequence {
        println("Generating first")
        yield(1)
        println("Generating second")
        yield(2)
        println("Generating third")
        yield(3)
        println("Done")
    }

fun main() {
    for (num in seq) {
        println("The next number is $num")
    }
}
