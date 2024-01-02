package me.bossm0n5t3r.coroutines.chapter06

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)
fun main() =
    runBlocking {
        val resultDeferred: Deferred<Int> =
            GlobalScope.async {
                delay(1000L)
                42
            }
        // do other stuff...
        val result: Int = resultDeferred.await() // (1 sec)
        println(result) // 42
        // or just
        println(resultDeferred.await()) // 42
    }
