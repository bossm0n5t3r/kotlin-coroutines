package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

suspend fun main() {
    val function =
        suspend {
            // this is suspending lambda expression
            delay(1000)
            "UserName"
        }

    function
        .asFlow()
        .collect { println(it) }
}

// (1 sec)
// UserName
