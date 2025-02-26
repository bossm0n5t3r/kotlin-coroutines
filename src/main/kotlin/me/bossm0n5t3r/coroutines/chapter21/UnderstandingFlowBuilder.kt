package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() =
    runBlocking {
        flow {
            // 1
            emit("A")
            emit("B")
            emit("C")
        }.collect { value ->
            // 2
            println(value)
        }
    }

// A
// B
// C
