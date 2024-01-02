package me.bossm0n5t3r.coroutines.chapter06

import kotlin.concurrent.thread

fun main() {
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        println("World! ${Thread.currentThread().name}")
    }
    println("Hello,")
    Thread.sleep(2000L)
}
