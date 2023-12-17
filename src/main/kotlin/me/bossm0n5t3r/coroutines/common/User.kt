package me.bossm0n5t3r.coroutines.common

data class User(
    val name: String,
    val age: Int = (20..80).random(),
)
