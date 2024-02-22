package me.bossm0n5t3r.coroutines.chapter20

fun interface FlowCollectorInUnderstandingFlow5 {
    suspend fun emit(value: String)
}

suspend fun main() {
    val f: suspend FlowCollectorInUnderstandingFlow5.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }
    f { print(it) } // ABC
    f { print(it) } // ABC
}
