package me.bossm0n5t3r.coroutines.chapter11

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.time.withTimeout
import java.time.Duration

private suspend fun test(): Int =
    withTimeout(Duration.ofMillis(1500)) {
        delay(Duration.ofMillis(1000))
        println("Still thinking")
        delay(Duration.ofMillis(1000))
        println("Done!")
        42
    }

suspend fun main(): Unit =
    coroutineScope {
        try {
            test()
        } catch (e: TimeoutCancellationException) {
            println("Cancelled")
        }
        delay(Duration.ofMillis(1000)) // Extra timeout does not help,
        // `test` body was cancelled
    }

// (1 sec)
// Still thinking
// (0.5 sec)
// Cancelled
