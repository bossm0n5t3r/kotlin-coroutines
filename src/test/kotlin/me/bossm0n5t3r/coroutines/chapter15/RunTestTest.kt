package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RunTestTest {
    @Test
    fun `runTest example`() =
        runTest {
            assertEquals(0, currentTime)
            delay(1000)
            assertEquals(1000, currentTime)
        }

    @Test
    fun `runTest example 2`() =
        runTest {
            assertEquals(0, currentTime)
            coroutineScope {
                launch { delay(1000) }
                launch { delay(1500) }
                launch { delay(2000) }
            }
            assertEquals(2000, currentTime)
        }
}
