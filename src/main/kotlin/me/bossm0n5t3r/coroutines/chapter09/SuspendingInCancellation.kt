package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            try {
                delay(2000)
                println("Job is done")
            } finally {
                println("Finally")
                launch { // will be ignored
                    println("Will not be printed")
                }
                delay(1000) // here exception is thrown
                println("Will not be printed")
            }
        }
        delay(1000)
        job.cancelAndJoin()
        println("Cancel done")
    }
