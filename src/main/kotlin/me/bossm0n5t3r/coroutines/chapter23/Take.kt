package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take

suspend fun main() {
    ('A'..'Z').asFlow()
        .take(5) // [A, B, C, D, E]
        .collect { print(it) } // ABCDE
}
