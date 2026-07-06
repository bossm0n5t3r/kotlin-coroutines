package me.bossm0n5t3r.coroutines.chapter03

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Do not do this, potential memory leak
private var continuation: Continuation<Unit>? = null

@Suppress("SuspendCoroutineLacksCancellationGuarantees")
private suspend fun suspendAndSetContinuation() {
    suspendCoroutine<Unit> { cont -> continuation = cont }
}

suspend fun main() = coroutineScope {
    println("Before")

    launch {
        delay(1000.milliseconds)
        continuation?.resume(Unit)
    }

    suspendAndSetContinuation()
    println("After")
}
