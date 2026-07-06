package me.bossm0n5t3r.coroutines.chapter08.nosuppress

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job = Job()
    val name = CoroutineName("Some name")
    val scope = CoroutineScope(name + job)

    scope.launch {
        val childName = coroutineContext[CoroutineName]
        assert(childName == name) // true
        val childJob = coroutineContext[Job]
        assert(childJob == null) // false
        assert(childJob != job) // false
        assert(childJob == job.children.first()) // true
    }
}
