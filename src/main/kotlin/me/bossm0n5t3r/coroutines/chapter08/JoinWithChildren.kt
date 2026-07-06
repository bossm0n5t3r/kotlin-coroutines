package me.bossm0n5t3r.coroutines.chapter08

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch {
        delay(1000.milliseconds)
        println("Test1")
    }
    launch {
        delay(2000.milliseconds)
        println("Test2")
    }

    val children = coroutineContext[Job]?.children

    val childrenNum = children?.count()
    println("Number of children: $childrenNum")
    children?.forEach { it.join() }
    println("All tests are done")
}
