package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit =
    coroutineScope {
        val dispatcher =
            Dispatchers.Default
                .limitedParallelism(1)

        val job = Job()
        repeat(5) {
            launch(dispatcher + job) {
                Thread.sleep(1000)
            }
        }
        job.complete()
        val time = measureTimeMillis { job.join() }
        println("Took $time") // Took 5024
    }
