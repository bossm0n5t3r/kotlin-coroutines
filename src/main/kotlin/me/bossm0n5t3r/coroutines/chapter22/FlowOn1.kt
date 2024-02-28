package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

private fun usersFlow(): Flow<String> =
    flow {
        repeat(2) {
            val ctx = currentCoroutineContext()
            val name = ctx[CoroutineName]?.name
            emit("User$it in $name")
        }
    }

suspend fun main() {
    val users = usersFlow()
    withContext(CoroutineName("Name1")) {
        users.collect { println(it) }
    }
    withContext(CoroutineName("Name2")) {
        users.collect { println(it) }
    }
}

// User0 in Name1
// User1 in Name1
// User0 in Name2
// User1 in Name2
