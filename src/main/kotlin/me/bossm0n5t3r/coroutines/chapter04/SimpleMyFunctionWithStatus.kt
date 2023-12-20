package me.bossm0n5t3r.coroutines.chapter04

import kotlinx.coroutines.delay

suspend fun myFunctionWithStatus() {
    println("Before")
    var counter = 0
    delay(1000) // suspending
    counter++
    println("Counter: $counter")
    println("After")
}
