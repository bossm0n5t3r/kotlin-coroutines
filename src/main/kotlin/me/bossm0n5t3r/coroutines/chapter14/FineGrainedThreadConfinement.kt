package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    class UserDownloader(
        private val api: NetworkService,
    ) {
        private val users = mutableListOf<User>()
        private val dispatcher =
            Dispatchers.IO
                .limitedParallelism(1)

        suspend fun downloaded(): List<User> =
            withContext(dispatcher) {
                users.toList()
            }

        suspend fun fetchUser(id: Int) {
            val newUser = api.fetchUser(id)
            withContext(dispatcher) {
                users += newUser
            }
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
    print(downloader.downloaded().size) // 1000000
}
