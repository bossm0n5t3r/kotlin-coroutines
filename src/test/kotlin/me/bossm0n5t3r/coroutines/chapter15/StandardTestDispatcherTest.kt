package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
class StandardTestDispatcherTest {
    @Test
    fun `StandardTestDispatcher example`() {
        val scheduler = TestCoroutineScheduler()
        val testDispatcher = StandardTestDispatcher(scheduler)

        CoroutineScope(testDispatcher).launch {
            println("Some work 1")
            delay(1000)
            println("Some work 2")
            delay(1000)
            println("Coroutine done")
        }

        assertEquals("[0] Before", "[${scheduler.currentTime}] Before")
        scheduler.advanceUntilIdle()
        assertEquals("[2000] After", "[${scheduler.currentTime}] After")
    }

    @Test
    fun `StandardTestDispatcher can access TestCoroutineScheduler with the scheduler property`() {
        val dispatcher = StandardTestDispatcher()

        CoroutineScope(dispatcher).launch {
            println("Some work 1")
            delay(1000)
            println("Some work 2")
            delay(1000)
            println("Coroutine done")
        }

        assertEquals("[0] Before", "[${dispatcher.scheduler.currentTime}] Before")
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals("[2000] After", "[${dispatcher.scheduler.currentTime}] After")
    }

    @Test
    fun `advanceTimeBy and runCurrent example`() {
        val testDispatcher = StandardTestDispatcher()

        CoroutineScope(testDispatcher).launch {
            delay(1)
            println("Done1")
        }
        CoroutineScope(testDispatcher).launch {
            delay(2)
            println("Done2")
        }

        testDispatcher.scheduler.advanceTimeBy(2) // Done
        testDispatcher.scheduler.runCurrent() // Done2
    }

    @Test
    fun `advanceTimeBy and runCurrent complex example`() {
        val testDispatcher = StandardTestDispatcher()

        CoroutineScope(testDispatcher).launch {
            delay(2)
            print("Done")
        }

        CoroutineScope(testDispatcher).launch {
            delay(4)
            print("Done2")
        }

        CoroutineScope(testDispatcher).launch {
            delay(6)
            print("Done3")
        }

        for (i in 1..5) {
            print(".")
            testDispatcher.scheduler.advanceTimeBy(1)
            testDispatcher.scheduler.runCurrent()
        } // ..Done..Done2.
    }

    @Test
    fun `advanceUntilIdle example`() {
        val dispatcher = StandardTestDispatcher()

        CoroutineScope(dispatcher).launch {
            delay(1000)
            println("Coroutine done")
        }

        Thread.sleep(Random.nextLong(2000)) // Does not matter
        // how much time we wait here, it will not influence
        // the result

        val time =
            measureTimeMillis {
                println("[${dispatcher.scheduler.currentTime}] Before")
                dispatcher.scheduler.advanceUntilIdle()
                println("[${dispatcher.scheduler.currentTime}] After")
            }

        assertThat(time).isLessThan(50)
    }
}
