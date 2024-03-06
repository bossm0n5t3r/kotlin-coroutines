package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    val ints: Flow<Int> =
        flowOf(1, 2, 3)
            .onEach { delay(1000) }
    val doubles: Flow<Double> = flowOf(0.1, 0.2, 0.3)

    val together: Flow<Number> = merge(ints, doubles)
    together.collect { println(it) }
}

// 0.1
// 0.2
// 0.3
// (1 sec)
// 1
// (1 sec)
// 2
// (1 sec)
// 3
