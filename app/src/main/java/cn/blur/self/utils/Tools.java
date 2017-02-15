package cn.blur.self.utils;

import android.annotation.SuppressLint;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MyPC on 2017/2/15.
 */

public class Tools {

    /**
     * 获取32位MD5
     *
     * @param value
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getMD5(String value) {

        String hashtext = null;
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(value.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            hashtext = hashtext.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (hashtext == null) {
            return "null";
        }

        return hashtext;
    }

}
