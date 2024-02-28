package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.flow.asFlow

suspend fun main() {
    listOf(1, 2, 3, 4, 5)
        .asFlow()
        .collect { print(it) } // 12345

    println()

    setOf(1, 2, 3, 4, 5)
        .asFlow()
        .collect { print(it) } // 12345

    println()

    sequenceOf(1, 2, 3, 4, 5)
        .asFlow()
        .collect { print(it) } // 12345
}
