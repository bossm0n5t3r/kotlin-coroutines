package me.bossm0n5t3r.coroutines.chapter06

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        delay(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    runBlocking {
        delay(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    runBlocking {
        delay(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    println("Hello,")
}
