package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job

fun main() {
    val name1 = "Name1"
    val ctx = CoroutineName(name1) + Job()
    assert(ctx[CoroutineName]?.name == name1) // Name1
    assert(ctx[Job]?.isActive == true) // true

    val ctx2 = ctx.minusKey(CoroutineName)
    assert(ctx2[CoroutineName]?.name == null) // null
    assert(ctx2[Job]?.isActive == true) // true

    val name2 = "Name2"
    val ctx3 =
        (ctx + CoroutineName(name2))
            .minusKey(CoroutineName)
    assert(ctx3[CoroutineName]?.name == null) // null
    assert(ctx3[Job]?.isActive == true) // true
}
