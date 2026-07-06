package me.bossm0n5t3r.coroutines.chapter08.nosuppress

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val parentJob = coroutineContext[Job]
    val scope = CoroutineScope(coroutineContext + Job(parentJob))
    scope.launch {
        // the new job replaces one from parent
        delay(1000.milliseconds)
        println("Will not be printed")
    }
} // (prints nothing, finishes immediately)
