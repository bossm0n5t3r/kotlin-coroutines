package me.bossm0n5t3r.coroutines.chapter02

import java.math.BigInteger

private val fibonacci: Sequence<BigInteger> =
    sequence {
        var first = BigInteger.ZERO
        var second = BigInteger.ONE
        while (true) {
            yield(first)
            val temp = first
            first += second
            second = temp
        }
    }

fun main() {
    print(fibonacci.take(10).toList())
}
