package me.bossm0n5t3r.coroutines.chapter10

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main(): Unit = runBlocking {
    supervisorScope {
        launch {
            delay(1000.milliseconds)
            throw Error("Some error")
        }

        launch {
            delay(2000.milliseconds)
            println("Will be printed")
        }
    }
    delay(1000.milliseconds)
    println("Done")
}
