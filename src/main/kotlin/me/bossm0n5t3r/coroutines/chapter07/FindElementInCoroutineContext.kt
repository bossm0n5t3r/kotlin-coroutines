package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

fun main() {
    val name = "A name"
    val ctx: CoroutineContext = CoroutineName(name)

    val coroutineName: CoroutineName? = ctx[CoroutineName]
    // or ctx.get(CoroutineName)
    assert(coroutineName?.name == name) // A name
    val job: Job? = ctx[Job] // or ctx.get(Job)
    assert(job == null) // null
}
