package me.bossm0n5t3r.coroutines.chapter11

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("DuplicatedCode")
private suspend fun longTask() = coroutineScope {
    launch {
        delay(1000.milliseconds)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 1")
    }
    launch {
        delay(2000.milliseconds)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 2")
    }
}

fun main() =
    runBlocking(CoroutineName("Parent")) {
        println("Before")
        longTask()
        println("After")
    }

// Before
// (1 sec)
// [Parent] Finished task 1
// (1 sec)
// [Parent] Finished task 2
// After
