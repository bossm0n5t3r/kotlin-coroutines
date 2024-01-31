package me.bossm0n5t3r.coroutines.chapter14

internal interface NetworkService {
    suspend fun fetchUser(id: Int): User
}
