/**
Copyright (C) Felipe Alafy All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.backend

import org.jasypt.util.text.BasicTextEncryptor
import sun.misc.BASE64Encoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Encripta {
    private val reference = Reference()
    fun encriptar(senha:String): String {
        return try {
            val digest = MessageDigest.getInstance("MD5")
            digest.update(senha.toByteArray())
            val encoder = BASE64Encoder()
            encoder.encode(digest.digest())
        } catch (ns: NoSuchAlgorithmException) {
            ns.printStackTrace()
            senha
        }
    }

    fun encriptar2(toEncrypt:String):String {
        val bte = BasicTextEncryptor()
        bte.setPasswordCharArray((reference.key)?.toCharArray())
        return bte.encrypt(toEncrypt)
    }

    fun descriptografar(toDecrypt:String):String {
        val bte = BasicTextEncryptor()
        bte.setPasswordCharArray((reference.key)?.toCharArray())
        return bte.decrypt(toDecrypt)
    }
}