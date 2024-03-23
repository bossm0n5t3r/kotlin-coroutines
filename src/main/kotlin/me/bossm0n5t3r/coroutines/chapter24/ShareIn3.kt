package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val flow1 = flowOf("A", "B", "C")
        val flow2 =
            flowOf("D")
                .onEach { delay(1000) }

        val sharedFlow =
            merge(flow1, flow2).shareIn(
                scope = this,
                started = SharingStarted.Lazily,
            )

        delay(100)
        launch {
            sharedFlow.collect { println("#1 $it") }
        }
        delay(1000)
        launch {
            sharedFlow.collect { println("#2 $it") }
        }
    }

// (0.1 sec)
// #1 A
// #1 B
// #1 C
// (1 sec)
// #2 D
// #1 D
