package me.bossm0n5t3r.coroutines.chapter14

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference

suspend fun main() {
    class UserDownloader(
        private val api: NetworkService,
    ) {
        private val users = AtomicReference(listOf<User>())

        fun downloaded(): List<User> = users.get()

        suspend fun fetchUser(id: Int) {
            val newUser = api.fetchUser(id)
            users.getAndUpdate { it + newUser }
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
        repeat(100_000) { // FIXME times should be 1_000_000. But it is too slow
            launch {
                downloader.fetchUser(it)
            }
        }
    }
    print(downloader.downloaded().size) // 100_000 FIXME should be 1_000_000
}
