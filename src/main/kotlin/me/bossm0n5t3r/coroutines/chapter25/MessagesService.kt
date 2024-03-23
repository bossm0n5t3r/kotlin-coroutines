package me.bossm0n5t3r.coroutines.chapter25

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.shareIn

class MessagesService(
    messagesSource: Flow<Message>,
    scope: CoroutineScope,
) {
    private val source =
        messagesSource
            .shareIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(),
            )

    fun observeMessages(fromUserId: String) =
        source
            .filter { it.fromUserId == fromUserId }
}
