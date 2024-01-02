package me.bossm0n5t3r.coroutines.chapter06

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)
fun main() =
    runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World! ${Thread.currentThread().name}")
        }
        GlobalScope.launch {
            delay(1000L)
            println("World! ${Thread.currentThread().name}")
        }
        GlobalScope.launch {
            delay(1000L)
            println("World! ${Thread.currentThread().name}")
        }
        println("Hello,")
        delay(2000L) // still needed
    }

// Hello,
// World! DefaultDispatcher-worker-2
// World! DefaultDispatcher-worker-1
// World! DefaultDispatcher-worker-3
//
// Process finished with exit code 0
