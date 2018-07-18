package com.mlink.base.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串加密类
 */
public class EncyptUtil {
    private final static String ALGORITHM_SHA = "SHA";
    private final static String ALGORITHM_SHA1 = "SHA1";
    private final static String ALGORITHM_MD5 = "MD5";

    private static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String md5(String data) {
        return encypt(ALGORITHM_MD5, data);
    }

    /**
     * 加密
     *
     * @param data
     * @return
     */
    public static String sha(String data) {
        return encypt(ALGORITHM_SHA, data);
    }

    /**
     * 加密
     *
     * @param data
     * @return
     */
    public static String sha1(String data) {
        return encypt(ALGORITHM_SHA1, data);
    }

    /**
     * 字符串加密
     *
     * @param algorithm 加密算法
     * @param data      待加密的字符串
     * @return 加密后的字符串
     */
    public static String encypt(String algorithm, String data) {
        if (StringUtils.isEmpty(algorithm)) {
            throw new NullPointerException("algorithm can not be empty");
        }
        if (StringUtils.isEmpty(data)) {
            throw new NullPointerException("data can not be empty");
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            return byteArrayToHexString(messageDigest.digest(data.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 字节数组转换十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    /**
     * 字节转换十六进制字符
     *
     * @param b 字节
     * @return 十六进制字符
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }
}
