package me.bossm0n5t3r.coroutines.chapter08

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("CoroutineContextWithJob", "DEPRECATION")
fun main(): Unit = runBlocking {
    launch(Job()) {
        // the new job replaces one from parent
        delay(1000.milliseconds)
        println("Will not be printed")
    }
} // (prints nothing, finishes immediately)
