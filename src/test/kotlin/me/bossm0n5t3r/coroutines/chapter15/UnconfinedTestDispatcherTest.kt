package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UnconfinedTestDispatcherTest {
    @Test
    fun `StandardTestDispatcher vs UnconfinedTestDispatcher`() {
        CoroutineScope(StandardTestDispatcher()).launch {
            print("A")
            delay(1)
            print("B")
        }
        CoroutineScope(UnconfinedTestDispatcher()).launch {
            print("C")
            delay(1)
            print("D")
        }
        // only C will be printed
    }
}
