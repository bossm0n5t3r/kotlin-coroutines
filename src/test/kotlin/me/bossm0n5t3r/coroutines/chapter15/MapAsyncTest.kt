package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
class MapAsyncTest {
    private suspend fun <T, R> Iterable<T>.mapAsync(transformation: suspend (T) -> R): List<R> =
        coroutineScope {
            this@mapAsync
                .map { async { transformation(it) } }
                .awaitAll()
        }

    @Test
    fun `should map async and keep elements order`() =
        runTest {
            val transforms =
                listOf(
                    suspend {
                        delay(3000)
                        "A"
                    },
                    suspend {
                        delay(2000)
                        "B"
                    },
                    suspend {
                        delay(4000)
                        "C"
                    },
                    suspend {
                        delay(1000)
                        "D"
                    },
                )

            val res = transforms.mapAsync { it() }
            assertEquals(listOf("A", "B", "C", "D"), res)
            assertEquals(4000, currentTime)
        }

    @Test
    fun `should support context propagation`() =
        runTest {
            var ctx: CoroutineContext? = null

            val name1 = CoroutineName("Name 1")
            withContext(name1) {
                listOf("A").mapAsync {
                    // in transformation
                    ctx = currentCoroutineContext() // should be name1
                    it
                }
                assertEquals(name1, ctx?.get(CoroutineName))
            }

            val name2 = CoroutineName("Some name 2")
            withContext(name2) {
                listOf(1, 2, 3).mapAsync {
                    // in transformation
                    ctx = currentCoroutineContext() // should be name2
                    it
                }
                assertEquals(name2, ctx?.get(CoroutineName))
            }
        }

    @Test
    fun `should support cancellation`() =
        runTest {
            var job: Job? = null

            val parentJob =
                launch {
                    listOf("A").mapAsync {
                        job = currentCoroutineContext().job // refer to a job
                        delay(Long.MAX_VALUE)
                    }
                }

            delay(1000)
            parentJob.cancel()
            assertEquals(true, job?.isCancelled) // referred job should be cancelled
        }
}
