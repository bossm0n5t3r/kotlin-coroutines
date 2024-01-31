package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

suspend fun main() =
    coroutineScope {
        repeat(5) {
            launch {
                delayAndPrint()
            }
        }
    }

private val mutex = Mutex()

private suspend fun delayAndPrint() {
    mutex.lock()
    delay(1000)
    println("Done")
    mutex.unlock()
}

// (1 sec)
// Done
// (1 sec)
// Done
// (1 sec)
// Done
// (1 sec)
// Done
// (1 sec)
// Done
