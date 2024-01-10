package me.bossm0n5t3r.coroutines.chapter09

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main(): Unit =
    coroutineScope {
        val job = Job()
        launch(job) {
            try {
                delay(Random.nextLong(2000))
                println("Done")
            } finally {
                print("Will always be printed")
            }
        }
        delay(1000)
        job.cancelAndJoin()
    }
