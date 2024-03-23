package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val flow =
            flowOf("A", "B", "C", "D")
                .onStart { println("Started") }
                .onCompletion { println("Finished") }
                .onEach { delay(1000) }

        val sharedFlow =
            flow.shareIn(
                scope = this,
                started = SharingStarted.WhileSubscribed(),
            )

        delay(3000)
        launch {
            println("#1 ${sharedFlow.first()}")
        }
        launch {
            println("#2 ${sharedFlow.take(2).toList()}")
        }
        delay(3000)
        launch {
            println("#3 ${sharedFlow.first()}")
        }
    }

// (3 sec)
// Started
// (1 sec)
// #1 A
// (1 sec)
// #2 [A, B]
// Finished
// (1 sec)
// Started
// (1 sec)
// #3 A
// Finished
