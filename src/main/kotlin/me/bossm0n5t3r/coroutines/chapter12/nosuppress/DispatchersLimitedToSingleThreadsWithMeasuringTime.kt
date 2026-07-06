package me.bossm0n5t3r.coroutines.chapter12.nosuppress

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val dispatcher = Dispatchers.Default.limitedParallelism(1)

    val job = Job(coroutineContext[Job])
    val scope = CoroutineScope(dispatcher + job)
    repeat(5) { scope.launch { Thread.sleep(1000) } }
    job.complete()
    val time = measureTimeMillis { job.join() }
    println("Took $time") // Took 5024
}
