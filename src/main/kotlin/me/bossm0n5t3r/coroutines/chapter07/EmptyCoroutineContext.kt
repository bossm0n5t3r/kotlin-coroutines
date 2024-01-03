package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    val empty: CoroutineContext = EmptyCoroutineContext
    assert(empty[CoroutineName] == null) // null
    assert(empty[Job] == null) // null

    val name1 = "Name1"
    val ctxName = empty + CoroutineName(name1) + empty
    assert(ctxName[CoroutineName] == CoroutineName(name1)) // CoroutineName(Name1)
}
