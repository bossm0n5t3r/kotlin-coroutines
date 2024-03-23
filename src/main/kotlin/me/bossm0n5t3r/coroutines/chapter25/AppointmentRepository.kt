package me.bossm0n5t3r.coroutines.chapter25

import kotlinx.coroutines.flow.Flow

interface AppointmentRepository {
    fun observeAppointments(): Flow<AppointmentsEvent>
}
