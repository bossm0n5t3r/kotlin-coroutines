package me.bossm0n5t3r.coroutines.chapter16

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val channel =
            Channel<Int>(
                capacity = 2,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

        launch {
            repeat(5) { index ->
                channel.send(index * 2)
                delay(100)
                println("Sent")
            }
            channel.close()
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
// (0.1 sec)
// Sent
// (0.1 sec)
// Sent
// (1 - 4 * 0.1 = 0.6 sec)
// 6
// (1 sec)
// 8
