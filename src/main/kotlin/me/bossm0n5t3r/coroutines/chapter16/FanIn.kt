package me.bossm0n5t3r.coroutines.chapter16

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
private fun <T> CoroutineScope.fanIn(channels: List<ReceiveChannel<T>>): ReceiveChannel<T> =
    produce {
        for (channel in channels) {
            launch {
                for (elem in channel) {
                    send(elem)
                }
            }
        }
    }

private suspend fun sendString(
    channel: SendChannel<String>,
    text: String,
    time: Long,
) {
    while (true) {
        delay(time)
        channel.send(text)
    }
}

fun main() =
    runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, "foo", 200L) }
        launch { sendString(channel, "BAR!", 500L) }
        repeat(50) {
            println(channel.receive())
        }
        coroutineContext.cancelChildren()
    }

// (200 ms)
// foo
// (200 ms)
// foo
// (100 ms)
// BAR!
// (100 ms)
// foo
// (200 ms)
// ...
