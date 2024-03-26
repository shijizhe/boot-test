package com.ya.boottest.utils.crypto;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * AES 对称加密工具类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/18 13:59
 */
public class AESUtils {

    private static final byte[] ENCODE_KEY = {10, 23, 56, 89, 14, 92, 35, 78, 21, 45, 67, 12, 83, 54, 32, 19};

    private static final byte[] IV_KEY = {65, 110, 49, 27, 88, 3, 97, 15, 74, 52, 101, 22, 13, 8, 60, 46};

    private static final Padding padding1 = Padding.PKCS5Padding;
    private static final Mode mode1 = Mode.CBC;

    public static final int SALT_LENGTH = 16;

    private static final AES aes = new AES(mode1, padding1,
            new SecretKeySpec(ENCODE_KEY, "AES"),
            new IvParameterSpec(IV_KEY));

    public static String encryptBase64(String data) {
        return aes.encryptBase64(data);
    }

    public static String decryptBase64(String key) {
        byte[] decodeKey = Base64.decode(key);
        return aes.decryptStr(decodeKey);
    }


    public static String generateSalt(){
        return RandomUtil.randomString(SALT_LENGTH);
    }


    public static void main(String[] args) {
        String encrypt = encryptBase64("123456");
        String ee = decryptBase64(encrypt);
        System.out.println(encrypt);
        System.out.println(ee);
    }
}
