package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

private var counter = AtomicInteger()

fun main() =
    runBlocking {
        massiveRun {
            counter.incrementAndGet()
        }
        println(counter.get()) // 1000000
    }

private suspend fun massiveRun(action: suspend () -> Unit) =
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                repeat(1000) { action() }
            }
        }
    }
