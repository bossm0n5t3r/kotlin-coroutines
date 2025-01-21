package me.bossm0n5t3r.coroutines.chapter08

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            // the new job replaces one from parent
            delay(1000)
            println("Text 1")
        }
        launch(job) {
            // the new job replaces one from parent
            delay(2000)
            println("Text 2")
        }
        job.join() // Here we will await forever
        println("Will not be printed")
    }
