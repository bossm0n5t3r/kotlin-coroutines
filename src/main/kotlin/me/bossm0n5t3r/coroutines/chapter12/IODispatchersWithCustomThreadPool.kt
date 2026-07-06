package me.bossm0n5t3r.coroutines.chapter12

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        printCoroutinesTime(Dispatchers.IO)
        // Dispatchers.IO took: 2020
    }

    launch {
        val dispatcher = Dispatchers.IO.limitedParallelism(100)
        printCoroutinesTime(dispatcher)
        // LimitedDispatcher@5ea9eecf took: 1026
    }
}

private suspend fun printCoroutinesTime(dispatcher: CoroutineDispatcher) {
    val test = measureTimeMillis {
        coroutineScope { repeat(100) { launch(dispatcher) { Thread.sleep(1000) } } }
    }
    println("$dispatcher took: $test")
}
