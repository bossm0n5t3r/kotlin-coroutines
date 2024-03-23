package me.bossm0n5t3r.coroutines.chapter25

data class AppointmentsUpdate(
    override val appointments: List<Appointment>,
) : AppointmentsEvent
