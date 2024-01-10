package me.bossm0n5t3r.coroutines.chapter10

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit =
    runBlocking {
        val job = SupervisorJob()
        launch(job) {
            delay(1000)
            throw Error("Some error")
        }
        launch(job) {
            delay(2000)
            println("Will be printed")
        }
        job.join()
        println("Done") // will not be printed
    }
