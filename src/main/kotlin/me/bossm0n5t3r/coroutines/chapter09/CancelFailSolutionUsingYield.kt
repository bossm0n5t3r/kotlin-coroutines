package me.bossm0n5t3r.coroutines.chapter09

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@Suppress("CoroutineContextWithJob", "DEPRECATION")
suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_000) { i ->
            Thread.sleep(200)
            yield()
            println("Printing $i")
        }
    }
    delay(1100.milliseconds)
    job.cancelAndJoin()
    println("Cancelled successfully")
    delay(1000.milliseconds)
}
