package me.bossm0n5t3r.coroutines.chapter25

class ApiException(
    val code: Int,
    message: String,
) : Throwable("Fake API exception - code: $code, message: $message")
