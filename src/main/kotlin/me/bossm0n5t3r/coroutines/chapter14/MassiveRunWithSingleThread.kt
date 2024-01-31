package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
private val dispatcher =
    Dispatchers.IO
        .limitedParallelism(1)

private var counter = 0

fun main() =
    runBlocking {
        massiveRun {
            withContext(dispatcher) {
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
