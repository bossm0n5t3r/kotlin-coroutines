package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import java.util.concurrent.Executor
import kotlin.coroutines.CoroutineContext

object LoomDispatcher : ExecutorCoroutineDispatcher() {
    val Dispatchers.LOOM: CoroutineDispatcher
        get() = LoomDispatcher

    override val executor: Executor =
        Executor { command ->
            Thread.startVirtualThread(command)
        }

    override fun dispatch(
        context: CoroutineContext,
        block: Runnable,
    ) {
        executor.execute(block)
    }

    override fun close() {
        error("Cannot be invoked on Dispatchers.LOOM")
    }
}
