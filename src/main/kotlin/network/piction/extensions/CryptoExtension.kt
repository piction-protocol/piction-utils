package network.piction.extensions

import network.piction.utils.AES256Util
import org.apache.commons.codec.digest.DigestUtils

val String.md5: String
    get() = DigestUtils.md5Hex(this)

fun String.encrypt(rawKey: String): String {
    return AES256Util(rawKey.md5).encrypt(this)
}

fun String.decrypt(rawKey: String): String {
    return AES256Util(rawKey.md5).decrypt(this)
}
