package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main() =
    coroutineScope {
        repeat(1000) {
            // runBlocking 은 디스패처가 설정되어 있지 않으면 자신만의 디스패처를 사용함
            launch { // or launch(Dispatchers.Default) {
                // To make it busy
                List(1000) { Random.nextLong() }.maxOrNull()

                val threadName = Thread.currentThread().name
                println("Running on thread: $threadName")
            }
        }
    }

// Running on thread: DefaultDispatcher-worker-3
// Running on thread: DefaultDispatcher-worker-7
// Running on thread: DefaultDispatcher-worker-10
// Running on thread: DefaultDispatcher-worker-5
// Running on thread: DefaultDispatcher-worker-2
// Running on thread: DefaultDispatcher-worker-6
// Running on thread: DefaultDispatcher-worker-8
// Running on thread: DefaultDispatcher-worker-9
// Running on thread: DefaultDispatcher-worker-10
// Running on thread: DefaultDispatcher-worker-2
// Running on thread: DefaultDispatcher-worker-10
// ...
