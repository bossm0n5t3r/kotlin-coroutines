package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            try {
                repeat(1_000) { i ->
                    delay(200)
                    println("Printing $i")
                }
            } catch (e: CancellationException) {
                println(e)
                throw e
            }
        }
        delay(1100)
        job.cancelAndJoin()
        println("Cancelled successfully")
        delay(1000)
    }
