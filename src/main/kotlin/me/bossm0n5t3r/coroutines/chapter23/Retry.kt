package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry

@Suppress("UNREACHABLE_CODE")
suspend fun main() {
    flow {
        emit(1)
        emit(2)
        error("E")
        emit(3)
    }.retry(3) {
        print(it.message)
        true
    }.collect { print(it) } // 12E12E12E12(exception thrown)
}
