package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

private val mutex = Mutex()

private var counter = 0

fun main() =
    runBlocking {
        massiveRun {
            mutex.withLock {
                counter++
            }
        }
        println(counter) // 1000000
    }

private suspend fun massiveRun(action: suspend () -> Unit) =
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                repeat(1000) { action() }
            }
        }
    }
