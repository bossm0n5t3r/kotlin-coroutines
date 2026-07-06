package me.bossm0n5t3r.coroutines.chapter08.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("DuplicatedCode")
fun main() = runBlocking {
    val parentJob = coroutineContext[Job]
    val job = Job(parentJob)
    val scope = CoroutineScope(coroutineContext + job)

    scope.launch {
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

    scope.launch { println("Will not be printed") }

    println("Done")
}
