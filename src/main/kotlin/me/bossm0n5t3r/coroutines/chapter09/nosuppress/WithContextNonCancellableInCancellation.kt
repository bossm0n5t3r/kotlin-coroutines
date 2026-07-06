package me.bossm0n5t3r.coroutines.chapter09.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main(): Unit = coroutineScope {
    val parentJob = coroutineContext[Job]
    val job = Job(parentJob)
    val scope = CoroutineScope(coroutineContext + job)
    scope.launch {
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
