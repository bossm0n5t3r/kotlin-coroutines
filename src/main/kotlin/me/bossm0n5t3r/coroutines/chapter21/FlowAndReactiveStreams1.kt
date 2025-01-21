package me.bossm0n5t3r.coroutines.chapter21

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.asFlow
import reactor.core.publisher.Flux

suspend fun main() =
    coroutineScope {
        Flux
            .range(1, 5)
            .asFlow()
            .collect { print(it) } // 12345

        println()

        Flowable
            .range(1, 5)
            .asFlow()
            .collect { print(it) } // 12345

        println()

        Observable
            .range(1, 5)
            .asFlow()
            .collect { print(it) } // 12345
    }
