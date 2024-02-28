package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

suspend fun main(): Unit =
    coroutineScope {
        flowOf("User1", "User2")
            .onStart { println("Users:") }
            .onEach { println(it) }
            .launchIn(this)
    }

// Users:
// User1
// User2
