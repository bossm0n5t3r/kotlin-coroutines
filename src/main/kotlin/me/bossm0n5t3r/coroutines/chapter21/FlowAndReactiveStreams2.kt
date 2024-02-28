package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.rx3.asFlowable
import kotlinx.coroutines.rx3.asObservable

suspend fun main(): Unit =
    coroutineScope {
        val flow = flowOf(1, 2, 3, 4, 5)

        flow.asFlux()
            .doOnNext { print(it) } // 12345
            .subscribe()

        println()

        flow.asFlowable()
            .subscribe { print(it) } // 12345

        println()

        flow.asObservable()
            .subscribe { print(it) } // 12345
    }
