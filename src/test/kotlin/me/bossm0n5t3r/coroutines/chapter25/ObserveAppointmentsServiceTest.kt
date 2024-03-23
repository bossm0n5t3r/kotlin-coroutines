package me.bossm0n5t3r.coroutines.chapter25

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.temporal.ChronoUnit

class ObserveAppointmentsServiceTest {
    class FakeAppointmentRepository(
        private val flow: Flow<AppointmentsEvent>,
    ) : AppointmentRepository {
        override fun observeAppointments() = flow
    }

    private val aDate1 = Instant.now().minus(2L, ChronoUnit.DAYS)
    private val anAppointment1 = Appointment("APP1", aDate1)
    private val aDate2 = Instant.now().minus(1L, ChronoUnit.DAYS)
    private val anAppointment2 = Appointment("APP2", aDate2)

    @Test
    fun `should keep only appointments from updates`() =
        runTest {
            // given
            val repo =
                FakeAppointmentRepository(
                    flowOf(
                        AppointmentsConfirmed,
                        AppointmentsUpdate(listOf(anAppointment1)),
                        AppointmentsUpdate(listOf(anAppointment2)),
                        AppointmentsConfirmed,
                    ),
                )
            val service = ObserveAppointmentsService(repo)

            // when
            val result = service.observeAppointments().toList()

            // then
            assertEquals(
                listOf(
                    listOf(anAppointment1),
                    listOf(anAppointment2),
                ),
                result,
            )
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should eliminate elements that same as the previous one`() =
        runTest {
            // given
            val repo =
                FakeAppointmentRepository(
                    flow {
                        delay(1000)
                        emit(AppointmentsUpdate(listOf(anAppointment1)))
                        emit(AppointmentsUpdate(listOf(anAppointment1)))
                        delay(1000)
                        emit(AppointmentsUpdate(listOf(anAppointment2)))
                        delay(1000)
                        emit(AppointmentsUpdate(listOf(anAppointment2)))
                        emit(AppointmentsUpdate(listOf(anAppointment1)))
                    },
                )
            val service = ObserveAppointmentsService(repo)

            // when
            val result =
                service.observeAppointments()
                    .map { currentTime to it }
                    .toList()

            // then
            assertEquals(
                listOf(
                    1000L to listOf(anAppointment1),
                    2000L to listOf(anAppointment2),
                    3000L to listOf(anAppointment1),
                ),
                result,
            )
        }

    @Test
    fun `should retry when API exception`() =
        runTest {
            // given
            val repo =
                FakeAppointmentRepository(
                    flow {
                        emit(AppointmentsUpdate(listOf(anAppointment1)))
                        throw ApiException(502, "Some message")
                    },
                )
            val service = ObserveAppointmentsService(repo)

            // when
            val result =
                service.observeAppointments()
                    .take(3)
                    .toList()

            // then
            assertEquals(
                listOf(
                    listOf(anAppointment1),
                    listOf(anAppointment1),
                    listOf(anAppointment1),
                ),
                result,
            )
        }

    @Test
    fun `should retry when API exception with the code 5XX`() =
        runTest {
            // given
            var retried = false
            val someException = object : Exception() {}
            val repo =
                FakeAppointmentRepository(
                    flow {
                        emit(AppointmentsUpdate(listOf(anAppointment1)))
                        if (!retried) {
                            retried = true
                            throw ApiException(502, "Some message")
                        } else {
                            throw someException
                        }
                    },
                )
            val service = ObserveAppointmentsService(repo)

            // when
            val result =
                service.observeAppointments()
                    .catch<Any> { emit(it) }
                    .toList()

            // then
            assertTrue(retried)
            assertEquals(
                listOf(
                    listOf(anAppointment1),
                    listOf(anAppointment1),
                    someException,
                ),
                result,
            )
        }
}
