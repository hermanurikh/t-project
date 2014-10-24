package ru.tsystems.tproject.controllers;

import java.security.MessageDigest;

/**
 * A controller that converts to MD5.
 */
public class Converter {
    public static String getMD5(String word) throws Exception{
        String password = "notParanoic" + word + "really";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] array = messageDigest.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
}
