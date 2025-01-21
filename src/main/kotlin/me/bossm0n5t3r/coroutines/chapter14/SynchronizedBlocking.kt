package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private var counter = 0

fun main() =
    runBlocking {
        val lock = Any()
        massiveRun {
            synchronized(lock) {
                // We are blocking threads!
                counter++
            }
        }
        println("Counter = $counter") // 1000000
    }

private suspend fun massiveRun(action: suspend () -> Unit) =
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                repeat(1000) { action() }
            }
        }
    }
