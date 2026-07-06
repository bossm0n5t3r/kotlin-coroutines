package me.bossm0n5t3r.coroutines.chapter08.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DuplicatedCode")
suspend fun main(): Unit = coroutineScope {
    val parentJob = coroutineContext[Job]
    val job = Job(parentJob)
    val scope = CoroutineScope(coroutineContext + job)
    scope.launch {
        // the new job replaces one from parent
        delay(1000.milliseconds)
        println("Text 1")
    }
    scope.launch {
        // the new job replaces one from parent
        delay(2000.milliseconds)
        println("Text 2")
    }
    job.join() // Here we will await forever
    println("Will not be printed")
}
