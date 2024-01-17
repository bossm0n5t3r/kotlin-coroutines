package me.bossm0n5t3r.coroutines.chapter11

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() =
    runBlocking {
        println("Before")

        withContext(SupervisorJob()) {
            launch {
                delay(1000)
                throw Error()
            }

            launch {
                delay(2000)
                println("Done")
            }
        }

        println("After")
    }

// Before
// (1 sec)
// Exception...
