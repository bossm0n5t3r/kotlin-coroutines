package me.bossm0n5t3r.coroutines.chapter08

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job1 = launch {
        delay(1000.milliseconds)
        println("Test1")
    }
    val job2 = launch {
        delay(2000.milliseconds)
        println("Test2")
    }

    job1.join()
    job2.join()
    println("All tests are done")
}
