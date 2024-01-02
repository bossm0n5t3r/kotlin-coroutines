package me.bossm0n5t3r.coroutines.chapter06

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
        delay(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    println("Hello,")
    Thread.sleep(2000L)
}
