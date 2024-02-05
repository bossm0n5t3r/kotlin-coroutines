package me.bossm0n5t3r.coroutines.chapter12

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import java.util.concurrent.Executors
import kotlin.coroutines.ContinuationInterceptor
import kotlin.test.Test

class SwitchDispatchersTest {
    private val dispatcher =
        Executors
            .newSingleThreadExecutor()
            .asCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    @DisplayName("Dispatchers.Main -> Dispatchers.Main.immediate")
    fun switchDispatchersTest0(): Unit =
        runTest {
            launch(Dispatchers.Main) {
                val mainDispatcher = coroutineContext[ContinuationInterceptor] as CoroutineDispatcher

                assert(mainDispatcher.toString() == "Dispatchers.Main")
                val rootThreadName = Thread.currentThread().name

                withContext(Dispatchers.Main.immediate) {
                    val mainImmediateDispatcher = coroutineContext[ContinuationInterceptor] as CoroutineDispatcher

                    assert(mainImmediateDispatcher.toString() == "Dispatchers.Main")
                    assert(mainDispatcher.toString() == mainImmediateDispatcher.toString())

                    val threadName = Thread.currentThread().name
                    assert(threadName == rootThreadName)
                }
            }
        }

    @Test
    @DisplayName("Dispatchers.Main -> Dispatchers.Unconfined")
    fun switchDispatchersTest1(): Unit =
        runTest {
            launch(Dispatchers.Main) {
                val mainDispatcher = coroutineContext[ContinuationInterceptor] as CoroutineDispatcher

                assert(mainDispatcher.toString() == "Dispatchers.Main")
                val rootThreadName = Thread.currentThread().name
                withContext(Dispatchers.Unconfined) {
                    val unconfinedDispatcher = coroutineContext[ContinuationInterceptor] as CoroutineDispatcher

                    assert(unconfinedDispatcher.toString() == "Dispatchers.Unconfined")
                    assert(mainDispatcher.toString() != unconfinedDispatcher.toString())

                    val threadName = Thread.currentThread().name
                    assert(threadName == rootThreadName)
                }
            }
        }
}
