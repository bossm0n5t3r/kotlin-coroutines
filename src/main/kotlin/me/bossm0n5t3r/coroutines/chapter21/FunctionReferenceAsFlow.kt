package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

private suspend fun getUserName(): String {
    delay(1000)
    return "UserName"
}

suspend fun main() {
    ::getUserName
        .asFlow()
        .collect { println(it) }
}

// (1 sec)
// UserName
