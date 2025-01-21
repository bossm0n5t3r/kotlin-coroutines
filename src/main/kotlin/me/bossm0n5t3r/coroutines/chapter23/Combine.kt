package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    val flow1 =
        flowOf("A", "B", "C")
            .onEach { delay(400) }
    val flow2 =
        flowOf(1, 2, 3, 4)
            .onEach { delay(1000) }
    flow1
        .combine(flow2) { f1, f2 -> "${f1}_$f2" }
        .collect { println(it) }
}

// (1 sec)
// B_1
// (0.2 sec)
// C_1
// (0.8 sec)
// C_2
// (1 sec)
// C_3
// (1 sec)
// C_4
