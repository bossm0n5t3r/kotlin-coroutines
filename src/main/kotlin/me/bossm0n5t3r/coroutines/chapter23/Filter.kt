package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter

suspend fun main() {
    (1..10).asFlow() // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        .filter { it <= 5 } // [1, 2, 3, 4, 5]
        .filter { isEven(it) } // [2, 4]
        .collect { print(it) } // 24
}

private fun isEven(num: Int): Boolean = num % 2 == 0
