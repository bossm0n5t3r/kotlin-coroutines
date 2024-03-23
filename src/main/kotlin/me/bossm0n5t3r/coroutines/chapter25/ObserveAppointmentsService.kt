package me.bossm0n5t3r.coroutines.chapter25

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry

class ObserveAppointmentsService(
    private val appointmentRepository: AppointmentRepository,
) {
    fun observeAppointments(): Flow<List<Appointment>> =
        appointmentRepository
            .observeAppointments()
            .filterIsInstance<AppointmentsUpdate>()
            .map { it.appointments }
            .distinctUntilChanged()
            .retry {
                it is ApiException && it.code in 500..599
            }
}
