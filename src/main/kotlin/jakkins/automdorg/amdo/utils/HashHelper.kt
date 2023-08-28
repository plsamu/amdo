package jakkins.automdorg.amdo.utils

import org.springframework.stereotype.Component
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Component
class HashHelper {
    @Throws(NoSuchAlgorithmException::class, IOException::class)
    fun calculateHash(inputStream: InputStream): String {
        val md: MessageDigest = MessageDigest.getInstance("SHA-256")
        val buffer = ByteArray(8192)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            md.update(buffer, 0, bytesRead)
        }
        val hash: ByteArray = md.digest()
        val hashHex = StringBuilder()
        for (b in hash) {
            hashHex.append(String.format("%02x", b))
        }
        return hashHex.toString()
    }
}