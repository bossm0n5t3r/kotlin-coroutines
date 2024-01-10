package me.bossm0n5t3r.coroutines.chapter10

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit =
    runBlocking {
        val scope = CoroutineScope(SupervisorJob())
        scope.launch {
            delay(1000)
            throw Error("Some error")
        }

        scope.launch {
            delay(2000)
            println("Will be printed")
        }

        delay(3000)
    }
