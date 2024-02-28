package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first

data class UserInChannelFlowExample2(val name: String)

interface UserApiInChannelFlowExample2 {
    suspend fun takePage(pageNumber: Int): List<UserInChannelFlowExample2>?
}

class FakeUserApiInChannelFlowExample2 : UserApiInChannelFlowExample2 {
    private val userInChannelFlowExample2s = List(20) { UserInChannelFlowExample2("User$it") }
    private val pageSize: Int = 3

    override suspend fun takePage(pageNumber: Int): List<UserInChannelFlowExample2> {
        delay(1000)
        return userInChannelFlowExample2s
            .drop(pageSize * pageNumber)
            .take(pageSize)
    }
}

fun allUsersFlowInChannelFlowExample2(api: UserApiInChannelFlowExample2): Flow<UserInChannelFlowExample2> =
    channelFlow {
        var page = 0
        do {
            println("Fetching page $page")
            val users = api.takePage(page++) // suspending
            users?.forEach { send(it) }
        } while (!users.isNullOrEmpty())
    }

suspend fun main() {
    val api = FakeUserApiInChannelFlowExample2()
    val users = allUsersFlowInChannelFlowExample2(api)
    val user =
        users
            .first {
                println("Checking $it")
                delay(1000)
                it.name == "User3"
            }
    println(user)
}

// Fetching page 0
// (1 sec)
// Checking UserInChannelFlowExample2(name=User0)
// Fetching page 1
// (1 sec)
// Checking UserInChannelFlowExample2(name=User1)
// Fetching page 2
// (1 sec)
// Checking UserInChannelFlowExample2(name=User2)
// Fetching page 3
// (1 sec)
// Checking UserInChannelFlowExample2(name=User3)
// Fetching page 4
// (1 sec)
// UserInChannelFlowExample2(name=User3)
