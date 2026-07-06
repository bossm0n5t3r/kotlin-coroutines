package me.bossm0n5t3r.coroutines.chapter06

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        delay(1000L.milliseconds)
        println("World! ${Thread.currentThread().name}")
    }
    runBlocking {
        delay(1000L.milliseconds)
        println("World! ${Thread.currentThread().name}")
    }
    runBlocking {
        delay(1000L.milliseconds)
        println("World! ${Thread.currentThread().name}")
    }
    println("Hello,")
}
