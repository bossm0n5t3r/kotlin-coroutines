package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MapAsyncTest {
    private suspend fun <T, R> Iterable<T>.mapAsync(transformation: suspend (T) -> R): List<R> =
        coroutineScope {
            this@mapAsync.map { async { transformation(it) } }
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
}
