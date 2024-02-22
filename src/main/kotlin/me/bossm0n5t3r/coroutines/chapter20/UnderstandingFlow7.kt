package me.bossm0n5t3r.coroutines.chapter20

fun interface FlowCollectorInUnderstandingFlow7 {
    suspend fun emit(value: String)
}

interface FlowInUnderstandingFlow7 {
    suspend fun collect(collector: FlowCollectorInUnderstandingFlow7)
}

fun flowInUnderstandingFlow7(builder: suspend FlowCollectorInUnderstandingFlow7.() -> Unit) =
    object : FlowInUnderstandingFlow7 {
        override suspend fun collect(collector: FlowCollectorInUnderstandingFlow7) {
            collector.builder()
        }
    }

suspend fun main() {
    val f: FlowInUnderstandingFlow7 =
        flowInUnderstandingFlow7 {
            emit("A")
            emit("B")
            emit("C")
        }
    f.collect { print(it) } // ABC
    f.collect { print(it) } // ABC
}
