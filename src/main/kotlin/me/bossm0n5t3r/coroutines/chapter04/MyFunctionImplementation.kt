package me.bossm0n5t3r.coroutines.chapter04

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume

private fun myFunction(continuation: Continuation<Unit>): Any {
    val continuation =
        continuation as? MyFunctionContinuation
            ?: MyFunctionContinuation(continuation)

    if (continuation.label == 0) {
        println("Before")
        continuation.label = 1
        if (delay(1000, continuation) == COROUTINE_SUSPENDED) {
            return COROUTINE_SUSPENDED
        }
    }
    if (continuation.label == 1) {
        println("After")
        return Unit
    }
    error("Impossible")
}

private class MyFunctionContinuation(
    val completion: Continuation<Unit>,
) : Continuation<Unit> {
    override val context: CoroutineContext
        get() = completion.context

    var label = 0
    var result: Result<Any>? = null

    override fun resumeWith(result: Result<Unit>) {
        this.result = result
        val res =
            try {
                val r = myFunction(this)
                if (r == COROUTINE_SUSPENDED) return
                Result.success(r as Unit)
            } catch (e: Throwable) {
                Result.failure(e)
            }
        completion.resumeWith(res)
    }
}

private val executor =
    Executors
        .newSingleThreadScheduledExecutor {
            Thread(it, "scheduler").apply { isDaemon = true }
        }

private fun delay(
    timeMillis: Long,
    continuation: Continuation<Unit>,
): Any {
    executor.schedule({
        continuation.resume(Unit)
    }, timeMillis, TimeUnit.MILLISECONDS)
    return COROUTINE_SUSPENDED
}

fun main() {
    val emptyContinuation =
        object : Continuation<Unit> {
            override val context: CoroutineContext =
                EmptyCoroutineContext

            override fun resumeWith(result: Result<Unit>) {
                // This is root coroutine, we don't need anything in this example
            }
        }
    myFunction(emptyContinuation)
    Thread.sleep(2000)
    // Needed to don't let the main finish immediately.
}

private val COROUTINE_SUSPENDED = Any()
