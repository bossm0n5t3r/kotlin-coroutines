package me.bossm0n5t3r.coroutines.chapter21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

data class UserInChannelFlowExample1(val name: String)

interface UserApiInChannelFlowExample1 {
    suspend fun takePage(pageNumber: Int): List<UserInChannelFlowExample1>
}

class FakeUserApiInChannelFlowExample1 : UserApiInChannelFlowExample1 {
    private val userInChannelFlowExample1s = List(20) { UserInChannelFlowExample1("User$it") }
    private val pageSize: Int = 3

    override suspend fun takePage(pageNumber: Int): List<UserInChannelFlowExample1> {
        delay(1000) // suspending
        return userInChannelFlowExample1s
            .drop(pageSize * pageNumber)
            .take(pageSize)
    }
}

fun allUsersFlowInChannelFlowExample1(api: UserApiInChannelFlowExample1): Flow<UserInChannelFlowExample1> =
    flow {
        var page = 0
        do {
            println("Fetching page $page")
            val users = api.takePage(page++) // suspending
            emitAll(users.asFlow())
        } while (users.isNotEmpty())
    }

suspend fun main() {
    val api = FakeUserApiInChannelFlowExample1()
    val users = allUsersFlowInChannelFlowExample1(api)
    val user =
        users
            .first {
                println("Checking $it")
                delay(1000) // suspending
                it.name == "User3"
            }
    println(user)
}

// Fetching page 0
// (1 sec)
// Checking UserInChannelFlowExample1(name=User0)
// (1 sec)
// Checking UserInChannelFlowExample1(name=User1)
// (1 sec)
// Checking UserInChannelFlowExample1(name=User2)
// (1 sec)
// Fetching page 1
// (1 sec)
// Checking UserInChannelFlowExample1(name=User3)
// (1 sec)
// UserInChannelFlowExample1(name=User3)
