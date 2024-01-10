package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main(): Unit =
    coroutineScope {
        val job =
            launch {
                delay(Random.nextLong(2400))
                println("Finished")
            }
        delay(800)
        job.invokeOnCompletion { exception: Throwable? ->
            println("Will always be printed")
            println("The exception was: $exception")
        }
        delay(800)
        job.cancelAndJoin()
    }
