package me.bossm0n5t3r.coroutines.chapter19

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Notice, that this function is not suspending
// and does not need CoroutineScope
private fun usersFlow(): Flow<String> =
    flow {
        repeat(3) {
            delay(1000)
            val ctx = currentCoroutineContext()
            val name = ctx[CoroutineName]?.name
            emit("User$it in $name")
        }
    }

suspend fun main() {
    val users = usersFlow()

    withContext(CoroutineName("Name")) {
        val job =
            launch {
                // collect is suspending
                users.collect { println(it) }
            }

        launch {
            delay(2100)
            println("I got enough")
            job.cancel()
        }
    }
}

// (1 sec)
// User0 in Name
// (1 sec)
// User1 in Name
// (0.1 sec)
// I got enough
