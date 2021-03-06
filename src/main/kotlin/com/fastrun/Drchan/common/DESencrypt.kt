package com.fastrun.TempCollection.common
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Scanner

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec

//import sun.misc.BASE64Decoder
//import sun.misc.BASE64Encoder
import org.apache.commons.codec.binary.Base64;

/*
 * AES对称加密和解密
 */
object AESEncoder {
    var salt="20180728"
    /*
   * 加密
   * 1.构造密钥生成器
   * 2.根据ecnodeRules规则初始化密钥生成器
   * 3.产生密钥
   * 4.创建和初始化密码器
   * 5.内容加密
   * 6.返回字符串
   */
    fun AESEncode(encodeRules: String, content: String): String? {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            val keygen = KeyGenerator.getInstance("AES")
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, SecureRandom(encodeRules.toByteArray()))
            //3.产生原始对称密钥
            val original_key = keygen.generateKey()
            //4.获得原始对称密钥的字节数组
            val raw = original_key.encoded
            //5.根据字节数组生成AES密钥
            val key = SecretKeySpec(raw, "AES")
            //6.根据指定算法AES自成密码器
            val cipher = Cipher.getInstance("AES")
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key)
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            val byte_encode = content.toByteArray(charset("utf-8"))
            //9.根据密码器的初始化方式--加密：将数据加密
            val byte_AES = cipher.doFinal(byte_encode)
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            //11.将字符串返回
            return Base64.encodeBase64String(byte_AES)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        //如果有错就返加nulll
        return null
    }

    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    fun AESDncode(encodeRules: String, content: String): String? {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            val keygen = KeyGenerator.getInstance("AES")
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, SecureRandom(encodeRules.toByteArray()))
            //3.产生原始对称密钥
            val original_key = keygen.generateKey()
            //4.获得原始对称密钥的字节数组
            val raw = original_key.encoded
            //5.根据字节数组生成AES密钥
            val key = SecretKeySpec(raw, "AES")
            //6.根据指定算法AES自成密码器
            val cipher = Cipher.getInstance("AES")
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key)
            //8.将加密并编码后的内容解码成字节数组
            val byte_content = Base64.decodeBase64(content)
            /*
             * 解密
             */
            val byte_decode = cipher.doFinal(byte_content)
            return String(byte_decode, charset("utf-8"))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }

        //如果有错就返加nulll
        return null
    }

}