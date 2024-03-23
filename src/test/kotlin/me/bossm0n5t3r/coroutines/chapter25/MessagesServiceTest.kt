package me.bossm0n5t3r.coroutines.chapter25

import app.cash.turbine.turbineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class MessagesServiceTest {
    @Disabled("Failing test!")
    @Test
    fun `should emit messages from user`() =
        runTest {
            // given
            val source =
                flowOf(
                    Message(fromUserId = "0", text = "A"),
                    Message(fromUserId = "1", text = "B"),
                    Message(fromUserId = "0", text = "C"),
                )
            val service =
                MessagesService(
                    messagesSource = source,
                    scope = backgroundScope,
                )

            // when
            val result =
                service.observeMessages("0")
                    .toList() // Here we'll wait forever!

            // then
            assertEquals(
                listOf(
                    Message(fromUserId = "0", text = "A"),
                    Message(fromUserId = "0", text = "C"),
                ),
                result,
            )
        }

    @Test
    fun `should emit messages from user using take`() =
        runTest {
            // given
            val source =
                flowOf(
                    Message(fromUserId = "0", text = "A"),
                    Message(fromUserId = "1", text = "B"),
                    Message(fromUserId = "0", text = "C"),
                )
            val service =
                MessagesService(
                    messagesSource = source,
                    scope = backgroundScope,
                )

            // when
            val result =
                service.observeMessages("0")
                    .take(2)
                    .toList()

            // then
            assertEquals(
                listOf(
                    Message(fromUserId = "0", text = "A"),
                    Message(fromUserId = "0", text = "C"),
                ),
                result,
            )
        }

    @Test
    fun `should emit messages from user using backgroundScope`() =
        runTest {
            // given
            val source =
                flow {
                    emit(Message(fromUserId = "0", text = "A"))
                    delay(1000)
                    emit(Message(fromUserId = "1", text = "B"))
                    emit(Message(fromUserId = "0", text = "C"))
                }
            val service =
                MessagesService(
                    messagesSource = source,
                    scope = backgroundScope,
                )

            // when
            val emittedMessages = mutableListOf<Message>()
            service.observeMessages("0")
                .onEach { emittedMessages.add(it) }
                .launchIn(backgroundScope)
            delay(1)

            // then
            assertEquals(
                listOf(
                    Message(fromUserId = "0", text = "A"),
                ),
                emittedMessages,
            )

            // when
            delay(1000)

            // then
            assertEquals(
                listOf(
                    Message(fromUserId = "0", text = "A"),
                    Message(fromUserId = "0", text = "C"),
                ),
                emittedMessages,
            )
        }

    private suspend fun <T> Flow<T>.toListDuring(duration: Duration): List<T> =
        coroutineScope {
            val result = mutableListOf<T>()
            val job =
                launch {
                    this@toListDuring.collect(result::add)
                }
            delay(duration)
            job.cancel()
            return@coroutineScope result
        }

    @Test
    fun `should emit messages from user using toListDuring`() =
        runTest {
            // given
            val source =
                flow {
                    emit(Message(fromUserId = "0", text = "A"))
                    emit(Message(fromUserId = "1", text = "B"))
                    emit(Message(fromUserId = "0", text = "C"))
                }
            val service =
                MessagesService(
                    messagesSource = source,
                    scope = backgroundScope,
                )

            // when
            val emittedMessages =
                service.observeMessages("0")
                    .toListDuring(1.milliseconds)

            // then
            assertEquals(
                listOf(
                    Message(fromUserId = "0", text = "A"),
                    Message(fromUserId = "0", text = "C"),
                ),
                emittedMessages,
            )
        }

    @Test
    fun `should emit messages from user using turbine`() =
        runTest {
            turbineScope {
                // given
                val source =
                    flow {
                        emit(Message(fromUserId = "0", text = "A"))
                        emit(Message(fromUserId = "1", text = "B"))
                        emit(Message(fromUserId = "0", text = "C"))
                    }
                val service =
                    MessagesService(
                        messagesSource = source,
                        scope = backgroundScope,
                    )

                // when
                val messagesTurbine =
                    service
                        .observeMessages("0")
                        .testIn(backgroundScope)

                // then
                assertEquals(
                    Message(fromUserId = "0", text = "A"),
                    messagesTurbine.awaitItem(),
                )
                assertEquals(
                    Message(fromUserId = "0", text = "C"),
                    messagesTurbine.awaitItem(),
                )
                messagesTurbine.expectNoEvents()
            }
        }

    private val infiniteFlow =
        flow<Nothing> {
            while (true) {
                delay(100)
            }
        }

    @Test
    fun `should start at most one connection`() =
        runTest {
            // given
            var connectionsCounter = 0
            val source =
                infiniteFlow
                    .onStart { connectionsCounter++ }
                    .onCompletion { connectionsCounter-- }
            val service =
                MessagesService(
                    messagesSource = source,
                    scope = backgroundScope,
                )

            // when
            service.observeMessages("0")
                .launchIn(backgroundScope)
            service.observeMessages("1")
                .launchIn(backgroundScope)
            service.observeMessages("0")
                .launchIn(backgroundScope)
            service.observeMessages("2")
                .launchIn(backgroundScope)
            delay(1000)

            // then
            assertEquals(1, connectionsCounter)
        }

    @Test
    fun `should start multiple connections to the source`() =
        runTest {
            // given
            var connectionsCounter = 0
            val source =
                infiniteFlow
                    .onStart { connectionsCounter++ }
                    .onCompletion { connectionsCounter-- }
            val service =
                MessagesService(
                    messagesSource = source,
                    scope = backgroundScope,
                )

            // when
            service.observeMessagesUsingMessagesSource("0")
                .launchIn(backgroundScope)
            service.observeMessagesUsingMessagesSource("1")
                .launchIn(backgroundScope)
            service.observeMessagesUsingMessagesSource("0")
                .launchIn(backgroundScope)
            service.observeMessagesUsingMessagesSource("2")
                .launchIn(backgroundScope)
            delay(1000)

            // then
            assertEquals(4, connectionsCounter)
        }
}
