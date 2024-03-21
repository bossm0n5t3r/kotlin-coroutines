package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val flow = flowOf("A", "B", "C")

        val sharedFlow: SharedFlow<String> =
            flow.shareIn(
                scope = this,
                started = SharingStarted.Eagerly,
            )

        delay(100)
        launch {
            sharedFlow.collect { println("#1 $it") }
        }
        print("Done")
    }

// (0.1 sec)
// Done
