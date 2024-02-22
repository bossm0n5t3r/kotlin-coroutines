package me.bossm0n5t3r.coroutines.chapter20

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlin.random.Random

private fun Flow<*>.counter() =
    flow<Int> {
        var counter = 0
        collect {
            counter++
            // to make it busy for a while
            List(100) { Random.nextLong() }.shuffled().sorted()
            emit(counter)
        }
    }

suspend fun main(): Unit =
    coroutineScope {
        val f1 = List(1000) { "$it" }.asFlow()
        val f2 =
            List(1000) { "$it" }.asFlow()
                .counter()

        launch { println(f1.counter().last()) } // 1000
        launch { println(f1.counter().last()) } // 1000
        launch { println(f2.last()) } // 1000
        launch { println(f2.last()) } // 1000
    }
