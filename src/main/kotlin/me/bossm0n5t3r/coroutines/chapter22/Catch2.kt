package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

private class MyErrorInCatch2 : Throwable("My error")

private val flowInCatch2 =
    flow {
        emit("Message1")
        throw MyErrorInCatch2()
    }

suspend fun main() {
    flowInCatch2
        .catch { emit("Error") }
        .collect { println("Collected $it") }
}

// Collected Message1
// Collected Error
