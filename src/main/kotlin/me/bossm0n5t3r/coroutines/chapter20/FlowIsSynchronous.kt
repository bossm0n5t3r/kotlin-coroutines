package me.bossm0n5t3r.coroutines.chapter20

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    flowOf("A", "B", "C")
        .onEach { delay(1000) }
        .collect { println(it) }
}

// (1 sec)
// A
// (1 sec)
// B
// (1 sec)
// C
