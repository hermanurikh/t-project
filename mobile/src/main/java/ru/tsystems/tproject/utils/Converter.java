package ru.tsystems.tproject.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A controllers that converts to MD5.
 */
public class Converter extends org.springframework.security.authentication.encoding.BasePasswordEncoder {
    /**
     * Converts the password to MD5 with two keys.
     * @param word the password;
     * @return the hash of the password;
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String word) throws NoSuchAlgorithmException{
        String password = "notParanoic" + word + "really";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] array = messageDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte anArray : array) {
            sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * The overriden method to check the validity.
     * @param s encoded password;
     * @param s2 password;
     * @param o random salt;
     * @return true or false
     */
    @Override
    public boolean isPasswordValid(String s, String s2, Object o) {
        try {
            return (s.equals(getMD5(s2)));
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
    }

    /**
     * The overriden method to encode the password.
     * @param s the password;
     * @param o random salt;
     * @return the encoded password.
     */
    @Override
    public String encodePassword(String s, Object o) {
        try {
            return getMD5(s);
        } catch (NoSuchAlgorithmException ex) {
            return s;
        }
    }
}
