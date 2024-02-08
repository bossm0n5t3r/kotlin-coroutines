package me.bossm0n5t3r.coroutines.common

import java.util.UUID

object RandomUtil {
    fun generateRandomString() = UUID.randomUUID().toString()
}
