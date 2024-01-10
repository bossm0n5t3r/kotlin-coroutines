package me.bossm0n5t3r.coroutines.chapter10

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

private object MyNonPropagatingException : CancellationException() {
    private fun readResolve(): Any = MyNonPropagatingException
}

suspend fun main(): Unit =
    coroutineScope {
        launch { // 1
            launch { // 2
                delay(2000)
                println("Will not be printed")
            }
            throw MyNonPropagatingException // 3
        }
        launch { // 4
            delay(2000)
            println("Will be printed")
        }
    }
