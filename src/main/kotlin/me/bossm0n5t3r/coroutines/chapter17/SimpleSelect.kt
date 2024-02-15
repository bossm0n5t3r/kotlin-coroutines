package me.bossm0n5t3r.coroutines.chapter17

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

private suspend fun requestData1(): String {
    delay(100_000)
    return "Data1"
}

private suspend fun requestData2(): String {
    delay(1000)
    return "Data2"
}

private val scope = CoroutineScope(SupervisorJob())

private suspend fun askMultipleForData(): String {
    val defData1 = scope.async { requestData1() }
    val defData2 = scope.async { requestData2() }
    return select {
        defData1.onAwait { it }
        defData2.onAwait { it }
    }
}

suspend fun main(): Unit =
    coroutineScope {
        println(askMultipleForData())
    }

// (1 sec)
// Data2
