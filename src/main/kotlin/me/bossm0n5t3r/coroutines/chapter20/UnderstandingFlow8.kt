package me.bossm0n5t3r.coroutines.chapter20

fun interface FlowCollectorInUnderstandingFlow8<T> {
    suspend fun emit(value: T)
}

interface FlowInUnderstandingFlow8<T> {
    suspend fun collect(collector: FlowCollectorInUnderstandingFlow8<T>)
}

fun <T> flowInUnderstandingFlow8(builder: suspend FlowCollectorInUnderstandingFlow8<T>.() -> Unit) =
    object : FlowInUnderstandingFlow8<T> {
        override suspend fun collect(collector: FlowCollectorInUnderstandingFlow8<T>) {
            collector.builder()
        }
    }

suspend fun main() {
    val f: FlowInUnderstandingFlow8<String> =
        flowInUnderstandingFlow8 {
            emit("A")
            emit("B")
            emit("C")
        }
    f.collect { print(it) } // ABC
    f.collect { print(it) } // ABC
}
