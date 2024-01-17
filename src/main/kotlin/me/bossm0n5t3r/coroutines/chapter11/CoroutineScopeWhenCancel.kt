package me.bossm0n5t3r.coroutines.chapter11

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private suspend fun longTask() =
    coroutineScope {
        launch {
            delay(1000)
            val name = coroutineContext[CoroutineName]?.name
            println("[$name] Finished task 1")
        }
        launch {
            delay(2000)
            val name = coroutineContext[CoroutineName]?.name
            println("[$name] Finished task 2")
        }
    }

fun main(): Unit =
    runBlocking {
        val job =
            launch(CoroutineName("Parent")) {
                longTask()
            }
        delay(1500)
        job.cancel()
    }

// [Parent] Finished task 1

// Process finished with exit code 0
