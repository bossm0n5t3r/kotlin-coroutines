package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() =
    coroutineScope {
        val job =
            launch {
                repeat(1_000) { i ->
                    delay(100)
                    Thread.sleep(100) // We simulate long operation
                    println("Printing $i")
                }
            }

        delay(1000)
        job.cancel()
        println("Cancelled successfully")
    }
