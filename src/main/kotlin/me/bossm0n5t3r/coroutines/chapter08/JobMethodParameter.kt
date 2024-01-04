package me.bossm0n5t3r.coroutines.chapter08

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val parentJob = Job()
        val job = Job(parentJob)
        launch(job) {
            delay(1000)
            println("Text 1")
        }
        launch(job) {
            delay(2000)
            println("Text 2") // This will not be printed
        }
        delay(1100)
        parentJob.cancel()
        job.children.forEach { it.join() }
    }
