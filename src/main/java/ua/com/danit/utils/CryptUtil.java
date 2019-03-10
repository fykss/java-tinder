package ua.com.danit.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class CryptUtil{

    private static String encrypt(String string){
        byte[] bytesString = new byte[0];
        try {
            bytesString = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bytesString);
    }

    private static String decrypt(String string){
        byte[] decode = Base64.getDecoder().decode(string);
        return new String(decode);
    }

    public static String encryptExtra(String string){
        return encrypt(string) + "@" + encrypt(Long.toString(System.currentTimeMillis()));
    }

    public static String decryptExtra(String string){
        String[] arrString = string.split("@");
        return decrypt(arrString[0]);
    }

    public static String decryptPrmName(HttpServletRequest req){
        Map<String, String[]> pm = req.getParameterMap();
        Set<String> strings = Stream.of(pm).map(Map::keySet).findFirst().get();
        return strings.stream().map(CryptUtil::decryptExtra).findFirst().get();
    }

    public static int decryptPrmValue(HttpServletRequest req){
        Map<String, String[]> pm = req.getParameterMap();
        Collection<String[]> strings = Stream.of(pm).map(Map::values).findFirst().get();
        return Integer.parseInt(strings.stream().map(s -> CryptUtil.decryptExtra(s[0])).findFirst().get());
    }
}
