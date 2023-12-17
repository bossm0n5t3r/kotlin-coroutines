package me.bossm0n5t3r.coroutines.chapter03

import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private class MyException : Throwable("Just an exception")

suspend fun main() {
    try {
        suspendCoroutine<Unit> { cont ->
            cont.resumeWithException(MyException())
        }
    } catch (e: MyException) {
        println("Caught!")
    }
}
