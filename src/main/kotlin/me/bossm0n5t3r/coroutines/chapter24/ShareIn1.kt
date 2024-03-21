package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val flow =
            flowOf("A", "B", "C")
                .onEach { delay(1000) }

        val sharedFlow: SharedFlow<String> =
            flow.shareIn(
                scope = this,
                started = SharingStarted.Eagerly,
                // replay = 0 (default)
            )

        delay(500)

        launch {
            sharedFlow.collect { println("#1 $it") }
        }

        delay(1000)

        launch {
            sharedFlow.collect { println("#2 $it") }
        }

        delay(1000)

        launch {
            sharedFlow.collect { println("#3 $it") }
        }
    }

// (1 sec)
// #1 A
// (1 sec)
// #2 B
// #1 B
// (1 sec)
// #1 C
// #2 C
// #3 C
