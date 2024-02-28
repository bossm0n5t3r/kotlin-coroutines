package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

private class MyErrorInUncaughtExceptions2 : Throwable("My error")

private val flowInUncaughtExceptions2 =
    flow {
        emit("Message1")
        emit("Message2")
    }

suspend fun main() {
    flowInUncaughtExceptions2
        .onStart { println("Before") }
        .catch { println("Caught $it") }
        .collect { throw MyErrorInUncaughtExceptions2() }
}

// Before
// Exception in thread "..." MyError: My error
