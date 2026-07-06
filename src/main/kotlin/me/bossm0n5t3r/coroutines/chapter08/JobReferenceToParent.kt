package me.bossm0n5t3r.coroutines.chapter08

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job: Job = launch { delay(1000.milliseconds) }

    val parentJob: Job = coroutineContext.job
    // or coroutineContext[Job]!!
    assert(job != parentJob) // false
    val parentChildren: Sequence<Job> = parentJob.children
    assert(parentChildren.first() == job) // true
}
