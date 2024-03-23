package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

suspend fun main(): Unit =
    coroutineScope {
        val flow =
            flowOf("A", "B")
                .onEach { delay(1000) }
                .onEach { println("Produced $it") }

        val stateFlow: StateFlow<String> =
            flow.stateIn(
                scope = this,
                started = SharingStarted.Lazily,
                initialValue = "Empty",
            )

        println(stateFlow.value)

        delay(2000)
        stateFlow.collect { println("Received $it") }
    }

// Empty
// (2 sec)
// Received Empty
// (1 sec)
// Produced A
// Received A
// (1 sec)
// Produced B
// Received B
