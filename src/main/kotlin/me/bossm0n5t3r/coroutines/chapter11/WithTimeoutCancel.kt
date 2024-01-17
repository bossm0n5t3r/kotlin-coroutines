package me.bossm0n5t3r.coroutines.chapter11

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

suspend fun main(): Unit =
    coroutineScope {
        launch { // 1
            launch { // 2, cancelled by its parent
                delay(2000)
                println("Will not be printed")
            }
            withTimeout(1000) { // we cancel launch
                delay(1500) // throw TimeoutCancellationException
            }
        }
        launch { // 3
            delay(2000)
            println("Done")
        }
    }

// (2 sec)
// Done
