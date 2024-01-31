package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    class UserDownloader(
        private val api: NetworkService,
    ) {
        private val users = mutableListOf<User>()

        fun downloaded(): List<User> = users.toList()

        suspend fun fetchUser(id: Int) {
            val newUser = api.fetchUser(id)
            users += newUser
        }
    }

    class FakeNetworkService : NetworkService {
        override suspend fun fetchUser(id: Int): User {
            delay(2)
            return User("User$id")
        }
    }

    val downloader = UserDownloader(FakeNetworkService())
    coroutineScope {
        repeat(1_000_000) {
            launch {
                downloader.fetchUser(it)
            }
        }
    }
    print(downloader.downloaded().size) // 815781, 805445, 753823, ...
}
