package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.CoroutineName
import kotlin.coroutines.CoroutineContext

fun main() {
    val name1 = "Name1"
    val ctx1: CoroutineContext = CoroutineName(name1)
    assert(ctx1[CoroutineName]?.name == name1) // Name1, 'CoroutineName' 가 키

    val name2 = "Name2"
    val ctx2: CoroutineContext = CoroutineName(name2)
    assert(ctx2[CoroutineName]?.name == name2) // Name2, 'CoroutineName' 가 키

    val ctx3 = ctx1 + ctx2
    assert(ctx3[CoroutineName]?.name == name2) // Name2
}
