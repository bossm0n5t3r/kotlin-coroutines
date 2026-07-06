package me.bossm0n5t3r.coroutines.chapter09

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch { delay(1000.milliseconds) }
    job.invokeOnCompletion { exception: Throwable? -> println("Finished") }
    delay(400.milliseconds)
    job.cancelAndJoin()
}
