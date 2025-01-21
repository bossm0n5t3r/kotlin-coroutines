package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var i = 0

suspend fun main(): Unit =
    coroutineScope {
        repeat(10_000) {
            launch(Dispatchers.IO) {
                // or Default
                i++
            }
        }
        delay(1000)
        println(i) // 9762, 9804, 9813, ...
    }
