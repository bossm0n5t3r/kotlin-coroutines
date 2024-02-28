package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

private class MyErrorInCatch1 : Throwable("My error")

private val flowInCatch1: Flow<Int> =
    flow {
        emit(1)
        emit(2)
        throw MyErrorInCatch1()
    }

suspend fun main() {
    flowInCatch1
        .onEach { println("Got $it") }
        .catch { println("Caught $it") }
        .collect { println("Collected $it") }
}

// Got 1
// Collected 1
// Got 2
// Collected 2
// Caught MyErrorInCatch1: My error
