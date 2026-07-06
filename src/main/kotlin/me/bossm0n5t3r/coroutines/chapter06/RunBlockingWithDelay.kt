package me.bossm0n5t3r.coroutines.chapter06

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("DuplicatedCode")
@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    GlobalScope.launch {
        delay(1000L.milliseconds)
        println("World! ${Thread.currentThread().name}")
    }
    GlobalScope.launch {
        delay(1000L.milliseconds)
        println("World! ${Thread.currentThread().name}")
    }
    GlobalScope.launch {
        delay(1000L.milliseconds)
        println("World! ${Thread.currentThread().name}")
    }
    println("Hello,")
    delay(2000L.milliseconds) // still needed
}

// Hello,
// World! DefaultDispatcher-worker-2
// World! DefaultDispatcher-worker-1
// World! DefaultDispatcher-worker-3
//
// Process finished with exit code 0
