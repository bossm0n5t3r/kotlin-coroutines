package me.bossm0n5t3r.coroutines.chapter15

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import me.bossm0n5t3r.coroutines.common.RandomUtil.generateRandomString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MockTest {
    @Test
    fun `should load data concurrently`() =
        runTest {
            // given
            val userRepo = mockk<UserDataRepository>()
            val aName = generateRandomString()
            val someFriends =
                listOf(
                    Friend(generateRandomString()),
                    Friend(generateRandomString()),
                    Friend(generateRandomString()),
                )
            val aProfile = Profile(generateRandomString())
            coEvery { userRepo.getName() } coAnswers {
                delay(600)
                aName
            }
            coEvery { userRepo.getFriends() } coAnswers {
                delay(700)
                someFriends
            }
            coEvery { userRepo.getProfile() } coAnswers {
                delay(800)
                aProfile
            }
            val useCase = FetchUserUseCase(userRepo)

            // when
            useCase.fetchUserData()

            // then
            assertEquals(800, currentTime)
        }
}
