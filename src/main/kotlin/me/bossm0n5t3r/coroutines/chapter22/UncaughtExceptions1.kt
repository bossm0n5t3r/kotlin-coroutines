package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.flow.flow

private class MyErrorInUncaughtExceptions1 : Throwable("My error")

private val flowInUncaughtExceptions1 =
    flow {
        emit("Message1")
        throw MyErrorInUncaughtExceptions1()
    }

suspend fun main() {
    try {
        flowInUncaughtExceptions1.collect { println("Collected $it") }
    } catch (e: MyErrorInUncaughtExceptions1) {
        println("Caught")
    }
}

// Collected Message1
// Caught
