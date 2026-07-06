package me.bossm0n5t3r.coroutines.chapter11

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("DuplicatedCode")
private suspend fun longTask() = coroutineScope {
    launch {
        delay(1000.milliseconds)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 1")
    }
    launch {
        delay(2000.milliseconds)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 2")
    }
}

fun main(): Unit = runBlocking {
    val job = launch(CoroutineName("Parent")) { longTask() }
    delay(1500.milliseconds)
    job.cancel()
}

// [Parent] Finished task 1

// Process finished with exit code 0
