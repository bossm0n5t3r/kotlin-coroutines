package me.bossm0n5t3r.coroutines.chapter08

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("CoroutineContextWithJob", "DEPRECATION")
fun main() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) { num ->
            delay(200.milliseconds)
            println("Rep$num")
        }
    }

    launch {
        delay(500.milliseconds)
        job.complete()
    }

    job.join()

    launch(job) { println("Will not be printed") }

    println("Done")
}
