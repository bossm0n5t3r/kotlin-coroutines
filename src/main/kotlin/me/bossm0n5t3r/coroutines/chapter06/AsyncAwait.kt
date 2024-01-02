package me.bossm0n5t3r.coroutines.chapter06

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@OptIn(DelicateCoroutinesApi::class)
fun main() =
    runBlocking {
        val res1 =
            GlobalScope.async {
                delay(1000L)
                "Text 1"
            }
        val res2 =
            GlobalScope.async {
                delay(3000L)
                "Text 2"
            }
        val res3 =
            GlobalScope.async {
                delay(2000L)
                "Text 3"
            }
        println(res1.await())
        println(res2.await())
        println(res3.await())
    }
