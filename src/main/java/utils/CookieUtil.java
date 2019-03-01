package utils;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public int getIdUser(Cookie[] cookies){
        int result = 0;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("tinderUser")){
                result = Integer.parseInt(cookie.getValue());
            }
        }
        return result;
    }

    public void killCookie(Cookie[] cookies){
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("tinderUser")){
                cookie.setMaxAge(0);
            }
        }
    }

    public boolean checkCookie(Cookie[] cookies){
        boolean flag = false;
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals("tinderUser")){
                    flag = true;
                }
            }
        }
        return flag;
    }

    public Cookie addCookie(String nameCookie, String valueCookie){
        Cookie cookie = new Cookie(nameCookie, valueCookie);
        cookie.setMaxAge(24*60*60);
        return cookie;
    }
}