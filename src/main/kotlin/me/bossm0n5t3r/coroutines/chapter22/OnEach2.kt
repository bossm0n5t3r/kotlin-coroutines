package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    flowOf(1, 2)
        .onEach { delay(1000) }
        .collect { println(it) }
}

// (1 sec)
// 1
// (1 sec)
// 2
