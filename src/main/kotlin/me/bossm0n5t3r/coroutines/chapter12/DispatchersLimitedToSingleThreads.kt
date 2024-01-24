package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var i = 0

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit =
    coroutineScope {
        val dispatcher =
            Dispatchers.Default
                .limitedParallelism(1)

        repeat(10000) {
            launch(dispatcher) {
                i++
            }
        }
        delay(1000)
        println(i) // 10000
    }
