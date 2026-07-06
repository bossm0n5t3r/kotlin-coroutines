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
    val scope = CoroutineScope(coroutineContext + SupervisorJob(parentJob))
    // Don't do that, SupervisorJob with one child
    // and no parent works similar to just Job
    scope.launch {
        // 1
        launch {
            delay(1000.milliseconds)
            throw Error("Some error")
        }

        launch {
            delay(2000.milliseconds)
            println("Will not be printed")
        }
    }

    delay(3000.milliseconds)
}
