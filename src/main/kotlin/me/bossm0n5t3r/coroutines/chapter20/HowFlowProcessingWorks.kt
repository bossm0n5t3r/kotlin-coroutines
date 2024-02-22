package me.bossm0n5t3r.coroutines.chapter20

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

private fun <T, R> Flow<T>.map(transformation: suspend (T) -> R): Flow<R> =
    flow {
        collect {
            emit(transformation(it))
        }
    }

suspend fun main() {
    flowOf("A", "B", "C")
        .map {
            delay(1000)
            it.lowercase()
        }
        .collect { println(it) }
}

// (1 sec)
// a
// (1 sec)
// b
// (1 sec)
// c
