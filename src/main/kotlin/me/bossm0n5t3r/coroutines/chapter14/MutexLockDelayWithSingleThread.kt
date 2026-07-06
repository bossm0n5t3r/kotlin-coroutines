package me.bossm0n5t3r.coroutines.chapter14

import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main() {
    class MessagesRepository {
        private val messages = mutableListOf<String>()
        private val dispatcher = Dispatchers.IO.limitedParallelism(1)

        suspend fun add(message: String) =
            withContext(dispatcher) {
                delay(1000.milliseconds) // we simulate network call
                messages.add(message)
            }
    }

    val repo = MessagesRepository()

    val timeMillis = measureTimeMillis {
        coroutineScope { repeat(5) { launch { repo.add("Message$it") } } }
    }
    println(timeMillis) // 1044, 1039, 1038, ...
}
