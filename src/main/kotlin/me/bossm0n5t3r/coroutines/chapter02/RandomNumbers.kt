package me.bossm0n5t3r.coroutines.chapter02

import kotlin.random.Random

private fun randomNumbers(seed: Long = System.currentTimeMillis()): Sequence<Int> =
    sequence {
        val random = Random(seed)
        while (true) {
            yield(random.nextInt())
        }
    }

fun main() {
    println(randomNumbers().take(10).toList())
}
