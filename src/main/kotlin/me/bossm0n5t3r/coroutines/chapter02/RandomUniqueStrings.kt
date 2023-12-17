package me.bossm0n5t3r.coroutines.chapter02

import kotlin.random.Random

private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')

private fun randomUniqueStrings(
    length: Int,
    seed: Long = System.currentTimeMillis(),
): Sequence<String> =
    sequence {
        val random = Random(seed)
        while (true) {
            val randomString =
                (1..length)
                    .map { _ -> random.nextInt(charPool.size) }
                    .map(charPool::get)
                    .joinToString("")
            yield(randomString)
        }
    }.distinct()

fun main() {
    println(randomUniqueStrings(10).take(10).toList())
}
