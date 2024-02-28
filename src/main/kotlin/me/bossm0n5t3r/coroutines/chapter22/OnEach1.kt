package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    flowOf(1, 2, 3, 4)
        .onEach { print(it) }
        .collect {} // 1234
}
