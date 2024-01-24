package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() =
    coroutineScope {
        repeat(1000) {
            launch(Dispatchers.IO) {
                Thread.sleep(200)

                val threadName = Thread.currentThread().name
                println("Running on thread: $threadName")
            }
        }
    }

// Running on thread: DefaultDispatcher-worker-30
// Running on thread: DefaultDispatcher-worker-2
// Running on thread: DefaultDispatcher-worker-15
// Running on thread: DefaultDispatcher-worker-1
// Running on thread: DefaultDispatcher-worker-7
// Running on thread: DefaultDispatcher-worker-32
// Running on thread: DefaultDispatcher-worker-11
// Running on thread: DefaultDispatcher-worker-16
// Running on thread: DefaultDispatcher-worker-22
// Running on thread: DefaultDispatcher-worker-10
// Running on thread: DefaultDispatcher-worker-31
// Running on thread: DefaultDispatcher-worker-13
// Running on thread: DefaultDispatcher-worker-8
// Running on thread: DefaultDispatcher-worker-24
// Running on thread: DefaultDispatcher-worker-3
// Running on thread: DefaultDispatcher-worker-12
// Running on thread: DefaultDispatcher-worker-27
// Running on thread: DefaultDispatcher-worker-14
// Running on thread: DefaultDispatcher-worker-9
// Running on thread: DefaultDispatcher-worker-18
// Running on thread: DefaultDispatcher-worker-17
// Running on thread: DefaultDispatcher-worker-19
// Running on thread: DefaultDispatcher-worker-5
// ...
