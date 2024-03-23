package me.bossm0n5t3r.coroutines.chapter25

object AppointmentsConfirmed : AppointmentsEvent {
    override val appointments: List<Appointment>
        get() = emptyList()
}
