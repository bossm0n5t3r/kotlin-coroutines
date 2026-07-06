package me.bossm0n5t3r.coroutines.chapter10

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope

private class MyException : Throwable()

suspend fun main() = supervisorScope {
    val str1 =
        async<String> {
            delay(1000.milliseconds)
            throw MyException()
        }

    val str2 = async {
        delay(2000.milliseconds)
        "Text2"
    }

    try {
        println(str1.await())
    } catch (e: MyException) {
        println(e)
    }

    println(str2.await())
}
