package me.bossm0n5t3r.coroutines.chapter03

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// Do not do this, potential memory leak
private var continuation: Continuation<Unit>? = null

private suspend fun suspendAndSetContinuation() {
    suspendCoroutine<Unit> { cont ->
        continuation = cont
    }
}

suspend fun main() =
    coroutineScope {
        println("Before")

        launch {
            delay(1000)
            continuation?.resume(Unit)
        }

        suspendAndSetContinuation()
        println("After")
    }
