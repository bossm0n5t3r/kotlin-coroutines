package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

fun main() {
    val name1 = "Name1"
    val ctx1: CoroutineContext = CoroutineName(name1)
    assert(ctx1[CoroutineName]?.name == name1) // Name1
    assert(ctx1[Job]?.isActive == null) // null

    val ctx2: CoroutineContext = Job()
    assert(ctx2[CoroutineName]?.name == null) // null
    assert(ctx2[Job]?.isActive == true) // true, because "Active"
    // is the default state of a job created this way

    val ctx3 = ctx1 + ctx2
    assert(ctx3[CoroutineName]?.name == name1) // Name1
    assert(ctx3[Job]?.isActive == true) // true
}
