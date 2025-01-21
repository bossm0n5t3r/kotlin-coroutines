package me.bossm0n5t3r.coroutines.chapter03

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() { // <- suspend 가 붙은 main
    println("Before")

    suspendCoroutine { continuation ->
        // <- 여기서 중단!
        println("Before too")
        continuation.resume(Unit) // <- 중단 이후 바로 재개
    }

    println("After") // <- resume 을 호출 했기 때문에 출력 가능
}
