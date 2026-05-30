package me.bossm0n5t3r.coroutines.chapter12

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() {
    val time = measureTimeMillis {
        coroutineScope { repeat(50) { launch(Dispatchers.IO) { Thread.sleep(1000) } } }
    }
    println(time) // 1038, 1045, 1038, ...
}
