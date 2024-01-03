package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

private suspend fun printName() {
    println(coroutineContext[CoroutineName]?.name)
}

suspend fun main() =
    withContext(CoroutineName("Outer")) {
        printName() // Outer
        launch(CoroutineName("Inner")) {
            printName() // Inner
        }
        delay(10)
        printName() // Outer
    }
