package me.bossm0n5t3r.coroutines.chapter15

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BackgroundScopeTest {
    @Test
    fun `should increment counter`() = runTest {
        var i = 0
        backgroundScope.launch {
            while (true) {
                delay(1000.milliseconds)
                i++
            }
        }

        delay(1001.milliseconds)
        assertEquals(1, i)
        delay(1000.milliseconds)
        assertEquals(2, i)
    }
}
