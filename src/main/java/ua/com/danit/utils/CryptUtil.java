package ua.com.danit.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Stream;

public class CryptUtil {

    public static String encrypt(String string) throws UnsupportedEncodingException {
        byte[] bytesString = string.getBytes("UTF-8");
        return Base64.getEncoder().encodeToString(bytesString);
    }

    public static String decrypt(String string){
        byte[] decode = Base64.getDecoder().decode(string);
        return new String(decode);
    }

    public static String decryptPrmName(Map<String, String[]> pm, HttpServletRequest req){
        pm = req.getParameterMap();
        return Stream.of(pm).map(stringMap -> stringMap.keySet()
                .stream().map(CryptUtil::decrypt).findFirst().get()).findFirst().get();
    }

    public static int decryptPrmValue(Map<String, String[]> pm, HttpServletRequest req){
        pm = req.getParameterMap();
        return Integer.parseInt(Stream.of(pm).map(stringMap -> stringMap.values()
                .stream().map(strings1 -> CryptUtil.decrypt(strings1[0])).findFirst().get()).findFirst().get());
    }

}
