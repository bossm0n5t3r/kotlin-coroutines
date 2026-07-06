package me.bossm0n5t3r.coroutines.chapter10.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val parentJob = coroutineContext[Job]
    val job = SupervisorJob(parentJob)
    val scope = CoroutineScope(coroutineContext + job)
    scope.launch {
        delay(1000.milliseconds)
        throw Error("Some error")
    }
    scope.launch {
        delay(2000.milliseconds)
        println("Will be printed")
    }
    job.join()
    println("Done") // will not be printed
}
