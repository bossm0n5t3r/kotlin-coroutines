package me.bossm0n5t3r.coroutines.chapter08

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit =
    runBlocking {
        val name = CoroutineName("Some name")
        val job = Job()

        launch(name + job) {
            val childName = coroutineContext[CoroutineName]
            assert(childName == name) // true
            val childJob = coroutineContext[Job]
            assert(childJob == null)
            assert(childJob != job) // false
            assert(childJob == job.children.first()) // true
        }
    }
