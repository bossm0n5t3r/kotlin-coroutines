package me.bossm0n5t3r.coroutines.chapter20

fun interface FlowCollectorInUnderstandingFlow6 {
    suspend fun emit(value: String)
}

interface FlowInUnderstandingFlow6 {
    suspend fun collect(collector: FlowCollectorInUnderstandingFlow6)
}

suspend fun main() {
    val builder: suspend FlowCollectorInUnderstandingFlow6.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }
    val flow: FlowInUnderstandingFlow6 =
        object : FlowInUnderstandingFlow6 {
            override suspend fun collect(collector: FlowCollectorInUnderstandingFlow6) {
                collector.builder()
            }
        }
    flow.collect { print(it) } // ABC
    flow.collect { print(it) } // ABC
}
