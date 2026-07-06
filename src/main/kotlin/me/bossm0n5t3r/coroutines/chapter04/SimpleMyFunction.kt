package me.bossm0n5t3r.coroutines.chapter04

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

suspend fun myFunction() {
    println("Before")
    delay(1000.milliseconds) // suspending
    println("After")
}
