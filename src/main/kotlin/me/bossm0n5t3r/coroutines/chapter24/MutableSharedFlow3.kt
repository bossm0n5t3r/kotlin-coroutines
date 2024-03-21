package me.bossm0n5t3r.coroutines.chapter24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val mutableSharedFlow = MutableSharedFlow<String>()
        val sharedFlow: SharedFlow<String> = mutableSharedFlow
        val collector: FlowCollector<String> = mutableSharedFlow

        launch {
            mutableSharedFlow.collect {
                println("#1 received $it")
            }
        }

        launch {
            sharedFlow.collect {
                println("#2 received $it")
            }
        }

        delay(1000)
        mutableSharedFlow.emit("Message1")
        collector.emit("Message2")
    }

// (1 sec)
// #2 received Message1
// #2 received Message2
// #1 received Message1
// #1 received Message2
