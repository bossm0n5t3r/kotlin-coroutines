package me.bossm0n5t3r.coroutines.chapter10

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch {
        launch {
            delay(1000.milliseconds)
            throw Error("Some error")
        }

        launch {
            delay(2000.milliseconds)
            println("Will not be printed")
        }

        launch {
            delay(500.milliseconds) // faster than the exception
            println("Will be printed")
        }
    }

    launch {
        delay(2000.milliseconds)
        println("Will not be printed")
    }
}
