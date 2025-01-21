package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop

suspend fun main() {
    ('A'..'Z')
        .asFlow()
        .drop(20) // [U, V, W, X, Y, Z]
        .collect { print(it) } // UVWXYZ
}
