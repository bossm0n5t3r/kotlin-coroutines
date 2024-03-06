package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

suspend fun main() {
    flowOf(1, 2, 3, 4)
        .onEach { delay(1000) }
        .scan(0) { acc, v -> acc + v }
        .collect { println(it) }
}

// 0
// (1 sec)
// 1
// (1 sec)
// 3
// (1 sec)
// 6
// (1 sec)
// 10
