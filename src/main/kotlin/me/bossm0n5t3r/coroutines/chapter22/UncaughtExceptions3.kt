package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

private class MyErrorInUncaughtExceptions3 : Throwable("My error")

private val flowInUncaughtExceptions3 =
    flow {
        emit("Message1")
        emit("Message2")
    }

suspend fun main() {
    flowInUncaughtExceptions3
        .onStart { println("Before") }
        .onEach { throw MyErrorInUncaughtExceptions3() }
        .catch { println("Caught $it") }
        .collect {}
}

// Before
// Caught MyError: My error
