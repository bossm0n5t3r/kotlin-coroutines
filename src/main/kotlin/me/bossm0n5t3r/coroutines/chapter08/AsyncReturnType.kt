package me.bossm0n5t3r.coroutines.chapter08

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(): Unit =
    runBlocking {
        val deferred: Deferred<String> =
            async {
                delay(1000)
                "Test"
            }
        val job: Job = deferred
    }
