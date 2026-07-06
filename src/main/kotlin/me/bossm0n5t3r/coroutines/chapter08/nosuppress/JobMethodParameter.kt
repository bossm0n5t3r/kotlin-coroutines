package me.bossm0n5t3r.coroutines.chapter08.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DuplicatedCode")
suspend fun main(): Unit = coroutineScope {
    val coroutineJob = coroutineContext[Job]
    val parentJob = Job(coroutineJob)
    val job = Job(parentJob)
    val scope = CoroutineScope(coroutineContext + job)
    scope.launch {
        delay(1000.milliseconds)
        println("Text 1")
    }
    scope.launch {
        delay(2000.milliseconds)
        println("Text 2") // This will not be printed
    }
    delay(1100.milliseconds)
    parentJob.cancel()
    job.children.forEach { it.join() }
}
