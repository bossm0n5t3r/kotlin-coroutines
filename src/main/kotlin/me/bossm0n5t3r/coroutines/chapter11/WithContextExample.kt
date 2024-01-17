package me.bossm0n5t3r.coroutines.chapter11

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private fun CoroutineScope.log(text: String) {
    val name = this.coroutineContext[CoroutineName]?.name
    println("[$name] $text")
}

fun main() =
    runBlocking(CoroutineName("Parent")) {
        log("Before")

        withContext(CoroutineName("Child 1")) {
            delay(1000)
            log("Hello 1")
        }

        withContext(CoroutineName("Child 2")) {
            delay(1000)
            log("Hello 2")
        }

        log("After")
    }

// [Parent] Before
// (1 sec)
// [Child 1] Hello 1
// (1 sec)
// [Child 2] Hello 2
// [Parent] After
