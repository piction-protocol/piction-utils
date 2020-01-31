import javax.crypto.BadPaddingException
import network.piction.extensions.md5
import network.piction.utils.AES256Util
import org.junit.Assert.assertEquals
import org.junit.Test

class AES256UtilTest {

    @Test(expected = IllegalArgumentException::class)
    fun testWithExpectedIllegalArgumentException() {
        AES256Util("key")
    }

    @Test(expected = BadPaddingException::class)
    fun testWithExpectedBadPaddingException() {
        val key = "key".md5
        val plainText = "text"
        val encryptedText = AES256Util(key).encrypt(key)
        val decryptedText = AES256Util(key.reversed()).decrypt(encryptedText)
        assertEquals(plainText, decryptedText)
    }

    @Test
    fun testSuccess() {
        val key = "key".md5
        val plainText = "text"
        val encryptedText = AES256Util(key).encrypt(plainText)
        val decryptedText = AES256Util(key).decrypt(encryptedText)
        assertEquals(plainText, decryptedText)
    }
}
