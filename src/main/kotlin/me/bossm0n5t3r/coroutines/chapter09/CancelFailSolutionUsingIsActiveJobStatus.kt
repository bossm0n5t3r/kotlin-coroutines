package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            do {
                Thread.sleep(200)
                println("Printing")
            } while (isActive)
        }
        delay(1100)
        job.cancelAndJoin()
        println("Cancelled successfully")
    }
