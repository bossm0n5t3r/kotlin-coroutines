package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

private suspend fun present(
    place: String,
    message: String,
) {
    val ctx = coroutineContext
    val name = ctx[CoroutineName]?.name
    println("[$name] $message on $place")
}

private fun messagesFlow(): Flow<String> =
    flow {
        present("flow builder", "Message")
        emit("Message")
    }

suspend fun main() {
    val users = messagesFlow()
    withContext(CoroutineName("Name1")) {
        users
            .flowOn(CoroutineName("Name3"))
            .onEach { present("onEach", it) }
            .flowOn(CoroutineName("Name2"))
            .collect { present("collect", it) }
    }
}

// [Name3] Message on flow builder
// [Name2] Message on onEach
// [Name1] Message on collect
