package me.bossm0n5t3r.coroutines.chapter22

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty

suspend fun main() =
    coroutineScope {
        flow<List<Int>> { delay(1000) }
            .onEmpty { emit(emptyList()) }
            .collect { println(it) }
    }

// (1 sec)
// []
