package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FetchUserDataTest {
    @Test
    fun `should construct user`() =
        runBlocking {
            // given
            val repo = FakeUserDataRepository()
            val useCase = FetchUserUseCase(repo)

            // when
            val result = useCase.fetchUserData()

            // then
            val expectedUser =
                User(
                    name = "Ben",
                    friends = listOf(Friend("some-friend-id-1")),
                    profile = Profile("Example description"),
                )
            assertEquals(expectedUser, result)
        }

    class FakeUserDataRepository : UserDataRepository {
        override suspend fun getName(): String = "Ben"

        override suspend fun getFriends(): List<Friend> = listOf(Friend("some-friend-id-1"))

        override suspend fun getProfile(): Profile = Profile("Example description")
    }
}
