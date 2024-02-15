package me.bossm0n5t3r.coroutines.chapter16

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit =
    coroutineScope {
        val channel =
            produce(capacity = 3) {
                repeat(5) { index ->
                    send(index * 2)
                    delay(100)
                    println("Sent")
                }
            }

        delay(1000)
        for (element in channel) {
            println(element)
            delay(1000)
        }
    }

// Sent
// (0.1 sec)
// Sent
// (0.1 sec)
// Sent
// (1 - 2 * 0.1 = 0.8 sec)
// 0
// Sent
// (1 sec)
// 2
// Sent
// (1 sec)
// 4
// (1 sec)
// 6
// (1 sec)
// 8
// (1 sec)
