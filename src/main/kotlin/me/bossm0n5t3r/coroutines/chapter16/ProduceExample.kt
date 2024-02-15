package me.bossm0n5t3r.coroutines.chapter16

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit =
    coroutineScope {
        val channel =
            produce {
                repeat(5) { index ->
                    println("Producing next one")
                    delay(1000)
                    send(index * 2)
                }
            }

        for (element in channel) {
            println(element)
        }
    }

// Producing next one
// (1 sec)
// 0
// Producing next one
// (1 sec)
// 2
// Producing next one
// (1 sec)
// 4
// Producing next one
// (1 sec)
// 6
// Producing next one
// (1 sec)
// 8
