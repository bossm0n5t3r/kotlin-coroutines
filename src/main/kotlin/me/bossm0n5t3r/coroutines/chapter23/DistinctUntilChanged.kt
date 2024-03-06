package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf

suspend fun main() {
    flowOf(1, 2, 2, 3, 2, 1, 1, 3)
        .distinctUntilChanged()
        .collect { print(it) } // 123213
}
