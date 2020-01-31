import javax.crypto.BadPaddingException
import network.piction.extensions.decrypt
import network.piction.extensions.encrypt
import network.piction.extensions.md5
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class CryptoExtensionTest {

    @Test(expected = BadPaddingException::class)
    fun testWithExpectedBadPaddingException() {
        val key = "key"
        val plainText = "text"
        val encryptedText = plainText.encrypt(key)
        val decryptedText = encryptedText.decrypt(key.reversed())
        assertEquals(plainText, decryptedText)
    }

    @Test
    fun testSuccess() {
        val key = "key"
        val plainText = "text"
        val encryptedText = plainText.encrypt(key)
        val decryptedText = encryptedText.decrypt(key)
        assertEquals(plainText, decryptedText)
    }

    @Test
    fun shotStringToMD5() {
        val key = "".md5
        assertThat(key.length, `is`(32))
    }

    @Test
    fun longStringToMD5() {
        val key = "long long long string".md5
        assertThat(key.length, `is`(32))
    }
}
