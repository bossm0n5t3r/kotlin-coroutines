package me.bossm0n5t3r.coroutines.chapter03

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// Do not do this
private var continuation: Continuation<Unit>? = null

private suspend fun suspendAndSetContinuation() {
    suspendCoroutine { cont ->
        continuation = cont
    }
}

suspend fun main() {
    println("Before")

    suspendAndSetContinuation()
    continuation?.resume(Unit) // <- 다른 스레드나 코루틴으로 재개하지 않아 실행된 상태로 유지

    println("After")
}
