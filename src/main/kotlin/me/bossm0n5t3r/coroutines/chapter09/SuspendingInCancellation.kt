package me.bossm0n5t3r.coroutines.chapter09

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("CoroutineContextWithJob", "DEPRECATION")
suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(2000.milliseconds)
            println("Job is done")
        } finally {
            println("Finally")
            launch {
                // will be ignored
                println("Will not be printed")
            }
            delay(1000.milliseconds) // here exception is thrown
            println("Will not be printed")
        }
    }
    delay(1000.milliseconds)
    job.cancelAndJoin()
    println("Cancel done")
}
