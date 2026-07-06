package me.bossm0n5t3r.coroutines.chapter09

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("CoroutineContextWithJob", "DEPRECATION")
suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(200.milliseconds)
            println("Coroutine finished")
        } finally {
            println("Finally")
            withContext(NonCancellable) {
                delay(1000L.milliseconds)
                println("Cleanup done")
            }
        }
    }
    delay(100.milliseconds)
    job.cancelAndJoin()
    println("Done")
}
