package me.bossm0n5t3r.coroutines.chapter12

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import me.bossm0n5t3r.coroutines.chapter12.LoomDispatcher.LOOM

suspend fun main() =
    measureTimeMillis {
            coroutineScope { repeat(100_000) { launch(Dispatchers.LOOM) { Thread.sleep(1000) } } }
        }
        .let(::println) // 2349, 2219, 2452, ...
