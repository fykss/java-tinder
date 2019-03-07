package ua.com.danit.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class EnCryptUtil {

    public static String encrypt(String string) throws UnsupportedEncodingException {
        byte[] bytesString = string.getBytes("UTF-8");
        return Base64.getEncoder().encodeToString(bytesString);
    }

    public static String decrypt(String string){
        byte[] decode = Base64.getDecoder().decode(string);
        return new String(decode);
    }

}
