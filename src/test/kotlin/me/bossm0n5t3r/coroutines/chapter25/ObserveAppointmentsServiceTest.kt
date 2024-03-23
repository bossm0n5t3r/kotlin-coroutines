package me.bossm0n5t3r.coroutines.chapter25

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
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
}
