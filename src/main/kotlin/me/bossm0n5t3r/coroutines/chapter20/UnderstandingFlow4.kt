package me.bossm0n5t3r.coroutines.chapter20

fun interface FlowCollectorInUnderstandingFlow4 {
    suspend fun emit(value: String)
}

suspend fun main() {
    val f: suspend (FlowCollectorInUnderstandingFlow4) -> Unit = {
        it.emit("A")
        it.emit("B")
        it.emit("C")
    }
    f { print(it) } // ABC
    f { print(it) } // ABC
}
