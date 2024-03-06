package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private fun flowFrom(elem: String) =
    flowOf(1, 2, 3)
        .onEach { delay(1000) }
        .map { "${it}_$elem " }

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    flowOf("A", "B", "C")
        .flatMapLatest { flowFrom(it) }
        .collect { println(it) }
}

// (1 sec)
// 1_C
// (1 sec)
// 2_C
// (1 sec)
// 3_C
