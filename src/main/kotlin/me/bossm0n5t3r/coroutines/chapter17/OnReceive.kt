package me.bossm0n5t3r.coroutines.chapter17

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

@OptIn(ExperimentalCoroutinesApi::class)
private fun CoroutineScope.produceString(
    s: String,
    time: Long,
) = produce {
    while (true) {
        delay(time)
        send(s)
    }
}

fun main() =
    runBlocking {
        val fooChannel = produceString("foo", 210L)
        val barChannel = produceString("BAR", 500L)

        repeat(7) {
            select {
                fooChannel.onReceive {
                    println("From fooChannel: $it")
                }
                barChannel.onReceive {
                    println("From barChannel: $it")
                }
            }
        }

        coroutineContext.cancelChildren()
    }

// From fooChannel: foo
// From fooChannel: foo
// From barChannel: BAR
// From fooChannel: foo
// From fooChannel: foo
// From barChannel: BAR
// From fooChannel: foo
