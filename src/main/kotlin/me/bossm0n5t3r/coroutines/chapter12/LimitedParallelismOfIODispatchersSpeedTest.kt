package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() =
    measureTimeMillis {
        val dispatcher =
            Dispatchers.IO
                .limitedParallelism(100_000)

        coroutineScope {
            repeat(100_000) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }.let(::println) // 22989
