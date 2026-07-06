package me.bossm0n5t3r.coroutines.chapter14

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

suspend fun main() = coroutineScope {
    val semaphore = Semaphore(2)

    repeat(5) {
        launch {
            semaphore.withPermit {
                delay(1000.milliseconds)
                print(it)
            }
        }
    }
}

// 01
// (1 sec)
// 32
// (1 sec)
// 4
