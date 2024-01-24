package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main(): Unit =
    coroutineScope {
        launch(Dispatchers.Default) {
            println(Thread.currentThread().name)
            withContext(Dispatchers.IO) {
                println(Thread.currentThread().name)
            }
        }
    }

// DefaultDispatcher-worker-1
// DefaultDispatcher-worker-1
