package com.aka.mybox.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aka on 2018/11/14
 * 使用Base64来保存和获取需加密数据
 */
public class Base64Utils {

    /**
     * BASE64加密
     */
    public static String encryptBASE64(String key) {
        int decodetime = 5;//压缩和解压的次数，防止被简单破解
        String encodeKey = "";
        try {
            while ((decodetime > 0)) {
                decodetime--;
                encodeKey = Base64.encodeToString(key.getBytes("utf-8"), Base64.NO_WRAP);
            }
            return encodeKey;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }

    }


    /**
     * BASE64解密
     */
    public static String decypBASE64(String encodeKey) {
        int decodetime = 5;//压缩和解压的次数，防止被简单破解
        String decodKey = "";
        try {
            while (decodetime > 0) {
                decodetime--;
                decodKey = new String(Base64.decode(encodeKey, Base64.NO_WRAP), "utf-8");
            }
            return decodKey;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
