package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    val list =
        flowOf(1, 2, 3, 4)
            .onEach { delay(1000) }
    val res = list.fold(0) { acc, i -> acc + i }
    println(res)
}

// (4 sec)
// 10
