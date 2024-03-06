package me.bossm0n5t3r.coroutines.chapter23

import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce

suspend fun main() {
    val flow =
        flowOf(1, 2, 3, 4) // [1, 2, 3, 4]
            .map { it * it } // [1, 4, 9, 16]

    println(flow.first()) // 1
    println(flow.count()) // 4

    println(flow.reduce { acc, value -> acc * value }) // 576
    println(flow.fold(0) { acc, value -> acc + value }) // 30
}
