package me.bossm0n5t3r.coroutines.chapter10

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("CoroutineContextWithJob", "DEPRECATION")
fun main(): Unit = runBlocking {
    // Don't do that, SupervisorJob with one child
    // and no parent works similar to just Job
    launch(SupervisorJob()) {
        // 1
        launch {
            delay(1000.milliseconds)
            throw Error("Some error")
        }

        launch {
            delay(2000.milliseconds)
            println("Will not be printed")
        }
    }

    delay(3000.milliseconds)
}
