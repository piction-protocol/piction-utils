package network.piction.extensions

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

val BigInteger.cleanDecimal: BigDecimal
    get() = this.toBigDecimal().divide(BigDecimal.TEN.pow(18))

val BigDecimal.appendDecimal: BigInteger
    get() = this.multiply(BigDecimal.TEN.pow(18)).toBigInteger()

val BigDecimal.pretty: BigDecimal
    get() = this.setScale(2, RoundingMode.DOWN).stripTrailingZeros()

val BigDecimal.toPercent: BigDecimal
    get() {
        if (this > BigDecimal("1")) {
            throw Exception()
        }
        return (this * BigDecimal("100")).stripTrailingZeros()
    }

fun BigDecimal.toString(format: String) = String.format(format, this)
