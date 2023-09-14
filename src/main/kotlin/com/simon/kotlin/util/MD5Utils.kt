package com.simon.kotlin.util

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.security.MessageDigest
import java.util.*

/**
 * MD5加密工具类
 */
object MD5Utils {

    @JvmStatic
    fun main(args: Array<String>) {
        val name = MD5Utils.getMD5(
            "46134efea308fdfff854632909ce8c25".uppercase() + "3fff6d7f1820eff6672872d2393b87c4".uppercase()
        ).uppercase()
        println(name)
    }

    @JvmStatic
    fun getMD5(str: String): String {
        var md5: MessageDigest? = null
        md5 = try {
            MessageDigest.getInstance("MD5")
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
        val charArray = str.toCharArray()
        val byteArray = ByteArray(charArray.size)
        for (i in charArray.indices) {
            byteArray[i] = charArray[i].toByte()
        }
        val md5Bytes = md5!!.digest(byteArray)
        val hexValue = StringBuffer()
        for (i in md5Bytes.indices) {
            val value = md5Bytes[i].toInt() and 0xff
            if (value < 16) {
                hexValue.append("0")
            }
            hexValue.append(Integer.toHexString(value))
        }
        return hexValue.toString()
    }

    @JvmStatic
    fun getFileMD5(path: String): String? {
        if (path.isNullOrEmpty()) {
            return null
        }
        var md5: MessageDigest? = null
        md5 = try {
            MessageDigest.getInstance("MD5")
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
        val inputStream: InputStream = FileInputStream(File(path))
        val md5sb = StringBuffer()
        val dataBytes = ByteArray(1024)
        var nread = 0
        while (inputStream.read(dataBytes).also { nread = it } != -1) {
            md5!!.update(dataBytes, 0, nread)
        }
        val md5Bytes = md5!!.digest()

        // convert the byte to hex format
        for (i in md5Bytes.indices) {
            md5sb.append(
                ((md5Bytes[i].toInt() and 0xff) + 0x100)
                    .toString(16)
                    .substring(1)
            )
        }
        return md5sb.toString().lowercase(Locale.getDefault())
    }

}