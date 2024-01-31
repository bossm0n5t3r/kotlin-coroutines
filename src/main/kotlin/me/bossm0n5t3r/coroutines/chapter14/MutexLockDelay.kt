package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

suspend fun main() {
    class MessagesRepository {
        private val messages = mutableListOf<String>()
        private val mutex = Mutex()

        suspend fun add(message: String) =
            mutex.withLock {
                delay(1000) // we simulate network call
                messages.add(message)
            }
    }

    val repo = MessagesRepository()

    val timeMillis =
        measureTimeMillis {
            coroutineScope {
                repeat(5) {
                    launch {
                        repo.add("Message$it")
                    }
                }
            }
        }
    println(timeMillis) // 5071, 5065, 5060, ...
}
