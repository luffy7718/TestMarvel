package com.example.testmarvel.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MarvelAPIUtils {
    companion object {
        fun generateHash(timestamp: String, publicKey: String, privateKey: String): String? {
            return try {
                val input = timestamp + privateKey + publicKey
                val md = MessageDigest.getInstance("MD5")
                val messageDigest = md.digest(input.toByteArray())
                val hexString = StringBuilder()
                for (b in messageDigest) {
                    val hex = Integer.toHexString(0xFF and b.toInt())
                    if (hex.length == 1) {
                        hexString.append('0')
                    }
                    hexString.append(hex)
                }
                hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                null
            }
        }
    }
}