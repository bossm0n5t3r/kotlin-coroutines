package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private var counter = 0

fun main() =
    runBlocking {
        massiveRun {
            counter++
        }
        println(counter) // 332598, 300346, 375650, ...
    }

private suspend fun massiveRun(action: suspend () -> Unit) =
    withContext(Dispatchers.Default) {
        repeat(1000) {
            launch {
                repeat(1000) { action() }
            }
        }
    }
