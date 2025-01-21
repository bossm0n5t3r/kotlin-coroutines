package me.bossm0n5t3r.coroutines.chapter03

import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() { // <- suspend 가 붙은 main
    println("Before")

    suspendCoroutine { continuation ->
        // <- 여기서 중단!
        thread {
            println("Suspended")
            Thread.sleep(1000)
            continuation.resume(Unit)
            println("Resumed") // <- resume 이후 바로 실행이 되지는 않음, After 이후 출력
        }
    }

    println("After") // <- 1초 후 바로 출력
}
