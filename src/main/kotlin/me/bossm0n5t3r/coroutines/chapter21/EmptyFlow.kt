package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.flow.emptyFlow

suspend fun main() {
    emptyFlow<Int>()
        .collect { print(it) } // (nothing)
}
