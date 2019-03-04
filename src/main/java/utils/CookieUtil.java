package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    public HttpServletResponse killCookie(Cookie[] cookies, HttpServletResponse resp){
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("tinderUser")){
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        return resp;
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
