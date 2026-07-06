package me.bossm0n5t3r.coroutines.chapter09.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val parentJob = coroutineContext[Job]
    val job = Job(parentJob)
    val scope = CoroutineScope(coroutineContext + job)
    scope.launch {
        repeat(1_000) { i ->
            delay(200.milliseconds)
            println("Printing $i")
        }
    }
    delay(1100.milliseconds)
    job.cancelAndJoin()
    println("Cancelled successfully")
}
