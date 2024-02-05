package me.bossm0n5t3r.coroutines.chapter15

internal interface UserDataRepository {
    suspend fun getName(): String

    suspend fun getFriends(): List<Friend>

    suspend fun getProfile(): Profile
}
