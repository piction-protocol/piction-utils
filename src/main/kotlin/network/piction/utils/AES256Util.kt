package network.piction.utils

import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.Key
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Base64

class AES256Util @Throws(UnsupportedEncodingException::class)
constructor(key: String) {

    private val algorithm = "AES/CBC/PKCS5Padding"
    private val charset = Charsets.UTF_8
    private val keySize = 256
    private val blockSize = 128
    private val keySpec: Key
    private val iv: String

    init {
        if (key.toByteArray(charset).size != keySize / 8) {
            throw IllegalArgumentException("'key' must be $keySize bit")
        }
        this.keySpec = SecretKeySpec(key.toByteArray(charset), "AES")
        this.iv = key.substring(0, blockSize / 8)
    }

    @Throws(
        UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun encrypt(plainText: String): String {
        val c = Cipher.getInstance(algorithm)
        c.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(iv.toByteArray()))
        val encrypted = c.doFinal(plainText.toByteArray(charset))
        return String(Base64.encodeBase64(encrypted))
    }

    @Throws(
        UnsupportedEncodingException::class,
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun decrypt(encryptedText: String): String {
        val c = Cipher.getInstance(algorithm)
        c.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(iv.toByteArray(charset)))
        val byteStr = Base64.decodeBase64(encryptedText.toByteArray())
        return String(c.doFinal(byteStr), charset)
    }
}
