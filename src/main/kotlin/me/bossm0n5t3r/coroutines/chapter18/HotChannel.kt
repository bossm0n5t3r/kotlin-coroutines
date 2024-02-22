package me.bossm0n5t3r.coroutines.chapter18

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
private fun CoroutineScope.makeChannel() =
    produce {
        println("Channel started")
        for (i in 1..3) {
            delay(1000)
            send(i)
        }
    }

suspend fun main() =
    coroutineScope {
        val channel = makeChannel()

        delay(1000)
        println("Calling channel...")
        for (value in channel) {
            println(value)
        }
        println("Consuming again...")
        for (value in channel) {
            println(value)
        }
    }

// Channel started
// (1 sec)
// Calling channel...
// 1
// (1 sec)
// 2
// (1 sec)
// 3
// Consuming again...
