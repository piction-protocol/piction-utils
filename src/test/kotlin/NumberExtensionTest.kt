import java.math.BigDecimal
import java.math.BigInteger
import network.piction.extensions.*
import org.junit.Assert.assertEquals
import org.junit.Test

class NumberExtensionTest {

    @Test
    fun testCleanDecimalAfterAppendDecimal() {
        val number = BigInteger("1000000000000000000")
        val cleanNumber = number.cleanDecimal
        val originalNumber = cleanNumber.appendDecimal
        assertEquals(number, originalNumber)
    }

    @Test
    fun testBigDecimalPretty1() {
        val number = BigDecimal("1.23").pretty
        assertEquals(number.toPlainString().length, 4)
    }

    @Test
    fun testBigDecimalPretty2() {
        val number = BigDecimal("1.230").pretty
        assertEquals(number.toPlainString().length, 4)
    }

    @Test
    fun testBigDecimalPretty3() {
        val number = BigDecimal("1.2301").pretty
        assertEquals(number.toPlainString().length, 4)
    }

    @Test
    fun testBigDecimalToPercent1() {
        val number = BigDecimal("0.01").toPercent
        println(BigDecimal("0.1").stripTrailingZeros() == BigDecimal("0.10").stripTrailingZeros())
        assertEquals(number, BigDecimal("1"))
    }

    @Test
    fun testBigDecimalToPercent2() {
        val number = BigDecimal("0.012").toPercent
        assertEquals(number, BigDecimal("1.2"))
    }

    @Test(expected = Exception::class)
    fun testBigDecimalToPercentWithExpectedException() {
        BigDecimal("10.01").toPercent
    }

    @Test
    fun testBigDecimalToString1() {
        val str = BigDecimal("1234567890.1234567890").toString("%,.18f")
        assertEquals(str, "1,234,567,890.123456789000000000")
    }

    @Test
    fun testBigDecimalToString2() {
        val str = BigDecimal("0").toString("%,.18f")
        assertEquals(str, "0.000000000000000000")
    }
}
