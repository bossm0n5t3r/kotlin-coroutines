package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            repeat(1000) { num ->
                Thread.sleep(200)
                ensureActive()
                println("Printing $num")
            }
        }
        delay(1100)
        job.cancelAndJoin()
        println("Cancelled successfully")
    }
