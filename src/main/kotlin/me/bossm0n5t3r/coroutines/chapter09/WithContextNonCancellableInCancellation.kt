package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            try {
                delay(200)
                println("Coroutine finished")
            } finally {
                println("Finally")
                withContext(NonCancellable) {
                    delay(1000L)
                    println("Cleanup done")
                }
            }
        }
        delay(100)
        job.cancelAndJoin()
        println("Done")
    }
