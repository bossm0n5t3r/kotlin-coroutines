package me.bossm0n5t3r.coroutines.chapter14

import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private var counter = AtomicInteger()

fun main() = runBlocking {
    massiveRun { counter.set(counter.get() + 1) }
    println(counter.get()) // 165668, 172644, 179273, ...
}

private suspend fun massiveRun(action: suspend () -> Unit) =
    withContext(Dispatchers.Default) { repeat(1000) { launch { repeat(1000) { action() } } } }
