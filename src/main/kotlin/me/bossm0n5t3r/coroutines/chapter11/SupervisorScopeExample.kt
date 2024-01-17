package me.bossm0n5t3r.coroutines.chapter11

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() =
    runBlocking {
        println("Before")

        supervisorScope {
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
// (1 sec)
// Done
// After
