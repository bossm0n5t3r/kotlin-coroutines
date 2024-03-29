package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

suspend fun main(): Unit =
    coroutineScope {
        val flow =
            flowOf("A", "B", "C")
                .onEach { delay(1000) }
                .onEach { println("Produced $it") }
        val stateFlow: StateFlow<String> = flow.stateIn(this)

        println("Listening")
        println(stateFlow.value)
        stateFlow.collect { println("Received $it") }
    }

// (1 sec)
// Produced A
// Listening
// A
// Received A
// (1 sec)
// Produced B
// Received B
// (1 sec)
// Produced C
// Received C
