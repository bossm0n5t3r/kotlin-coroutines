package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

suspend fun main() {
    flowOf(1, 2, 3) // [1, 2, 3]
        .map { it * it } // [1, 4, 9]
        .collect { print(it) } // 149
}
