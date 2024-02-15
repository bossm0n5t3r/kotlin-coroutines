package me.bossm0n5t3r.coroutines.chapter16

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val channel = Channel<Int>()
        launch {
            repeat(5) { index ->
                println("Producing next one")
                delay(1000)
                channel.send(index * 2)
            }
            channel.close()
        }

        launch {
            channel.consumeEach { element ->
                println(element)
            }
            // or
//            for (element in channel) {
//                println(element)
//            }
        }
    }

// Producing next one
// (1 sec)
// Producing next one
// 0
// (1 sec)
// Producing next one
// 2
// (1 sec)
// Producing next one
// 4
// (1 sec)
// Producing next one
// 6
// (1 sec)
// 8
