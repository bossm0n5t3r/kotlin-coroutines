package me.bossm0n5t3r.coroutines.chapter18

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

private fun makeFlow() =
    flow {
        println("Flow started")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

suspend fun main() =
    coroutineScope {
        val flow = makeFlow()

        delay(1000)
        println("Calling flow...")
        flow.collect { value -> println(value) }
        println("Consuming again...")
        flow.collect { value -> println(value) }
    }

// (1 sec)
// Calling flow...
// Flow started
// (1 sec)
// 1
// (1 sec)
// 2
// (1 sec)
// 3
// Consuming again...
// Flow started
// (1 sec)
// 1
// (1 sec)
// 2
// (1 sec)
// 3
