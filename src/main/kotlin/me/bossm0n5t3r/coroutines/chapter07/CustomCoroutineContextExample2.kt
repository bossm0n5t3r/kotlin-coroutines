package me.bossm0n5t3r.coroutines.chapter07

import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

private data class User(
    val id: String,
    val name: String,
)

private abstract class UuidProviderContext : CoroutineContext.Element {
    abstract fun nextUuid(): String

    override val key: CoroutineContext.Key<*> = Key

    companion object Key :
        CoroutineContext.Key<UuidProviderContext>
}

private class RealUuidProviderContext : UuidProviderContext() {
    override fun nextUuid(): String = UUID.randomUUID().toString()
}

private class FakeUuidProviderContext(
    private val fakeUuid: String,
) : UuidProviderContext() {
    override fun nextUuid(): String = fakeUuid
}

private suspend fun nextUuid(): String =
    checkNotNull(coroutineContext[UuidProviderContext]) {
        "UuidProviderContext not present"
    }.nextUuid()

// function under test
private suspend fun makeUser(name: String) =
    User(
        id = nextUuid(),
        name = name,
    )

suspend fun main() {
    // production case
    withContext(RealUuidProviderContext()) {
        println(makeUser("Michał"))
        // e.g. User(id=7b5766fe-2143-44be-a292-6522ffa81268, name=Michał)
    }

    // test case
    withContext(FakeUuidProviderContext("FAKE_UUID")) {
        val user = makeUser("Michał")
        println(user) // User(id=FAKE_UUID, name=Michał)
        assert(User("FAKE_UUID", "Michał") == user)
    }
}
