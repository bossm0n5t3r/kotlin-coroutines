package me.bossm0n5t3r.coroutines.chapter15

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import me.bossm0n5t3r.coroutines.common.RandomUtil.generateRandomString
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
class ChangingDispatcherTest {
    interface CsvReader {
        fun <T> readCsvBlocking(
            fileName: String,
            clazz: Class<T>,
        ): GameState
    }

    class GameState

    class SaveReader(
        private val reader: CsvReader,
    ) {
        suspend fun readSave(name: String): GameState =
            withContext(Dispatchers.IO) {
                reader.readCsvBlocking(name, GameState::class.java)
            }
    }

    @Test
    fun `should change dispatcher`() =
        runBlocking {
            // given
            val csvReader = mockk<CsvReader>()
            val startThreadName = "MyName"
            var usedThreadName: String? = null
            val aFileName = generateRandomString()
            val aGameState = GameState()
            every {
                csvReader.readCsvBlocking(
                    aFileName,
                    GameState::class.java,
                )
            } coAnswers {
                usedThreadName = Thread.currentThread().name
                aGameState
            }
            val saveReader = SaveReader(csvReader)

            // when
            withContext(newSingleThreadContext(startThreadName)) {
                saveReader.readSave(aFileName)
            }

            // then
            assertNotNull(usedThreadName)
            val expectedPrefix = "DefaultDispatcher-worker-"
            assert(usedThreadName!!.startsWith(expectedPrefix))
        }
}
