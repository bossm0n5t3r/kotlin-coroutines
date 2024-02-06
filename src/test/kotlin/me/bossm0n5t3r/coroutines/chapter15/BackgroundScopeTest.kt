package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BackgroundScopeTest {
    @Test
    fun `should increment counter`() =
        runTest {
            var i = 0
            backgroundScope.launch {
                while (true) {
                    delay(1000)
                    i++
                }
            }

            delay(1001)
            assertEquals(1, i)
            delay(1000)
            assertEquals(2, i)
        }
}
