package me.bossm0n5t3r.coroutines.chapter15

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FetchUserDataTest {
    @Test
    fun `should load data concurrently`() =
        runTest {
            // given
            val userRepo = FakeUserDataRepository()
            val useCase = FetchUserUseCase(userRepo)

            // when
            useCase.fetchUserData()

            // then
            assertEquals(1000, currentTime)
        }

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
        override suspend fun getName(): String {
            delay(1000)
            return "Ben"
        }

        override suspend fun getFriends(): List<Friend> {
            delay(1000)
            return listOf(Friend("some-friend-id-1"))
        }

        override suspend fun getProfile(): Profile {
            delay(1000)
            return Profile("Example description")
        }
    }
}
