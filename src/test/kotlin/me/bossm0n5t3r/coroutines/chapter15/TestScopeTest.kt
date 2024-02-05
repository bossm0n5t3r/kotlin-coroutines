package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runCurrent
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TestScopeTest {
    @Test
    fun `TestScope example`() {
        val scope = TestScope()

        scope.launch {
            delay(1000)
            println("First done")
            delay(1000)
            println("Coroutine done")
        }

        println("[${scope.currentTime}] Before") // [0] Before
        assertEquals(0, scope.currentTime)

        scope.advanceTimeBy(1000)
        scope.runCurrent() // First done
        println("[${scope.currentTime}] Middle") // [1000] Middle
        assertEquals(1000, scope.currentTime)

        scope.advanceUntilIdle() // Coroutine done
        println("[${scope.currentTime}] After") // [2000] After
        assertEquals(2000, scope.currentTime)
    }
}
