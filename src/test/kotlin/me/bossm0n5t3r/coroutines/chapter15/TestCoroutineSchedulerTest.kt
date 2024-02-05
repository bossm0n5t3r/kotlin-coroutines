package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TestCoroutineSchedulerTest {
    @Test
    fun `TestCoroutineScheduler example`() {
        val scheduler = TestCoroutineScheduler()

        assertEquals(0, scheduler.currentTime)

        scheduler.advanceTimeBy(1_000)
        assertEquals(1_000, scheduler.currentTime)

        scheduler.advanceTimeBy(1_000)
        assertEquals(2_000, scheduler.currentTime)
    }
}
