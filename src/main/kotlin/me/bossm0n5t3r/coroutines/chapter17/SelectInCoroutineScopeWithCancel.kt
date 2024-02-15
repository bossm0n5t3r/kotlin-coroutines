package me.bossm0n5t3r.coroutines.chapter17

import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

private suspend fun requestData1(): String {
    delay(10_000)
    return "Data1"
}

private suspend fun requestData2(): String {
    delay(1000)
    return "Data2"
}

private suspend fun askMultipleForData(): String =
    coroutineScope {
        select {
            async { requestData1() }.onAwait { it }
            async { requestData2() }.onAwait { it }
        }.also { coroutineContext.cancelChildren() }
    }

suspend fun main(): Unit =
    coroutineScope {
        println(askMultipleForData())
    }

// (1 sec)
// Data2
