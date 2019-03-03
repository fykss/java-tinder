package servlet;

import utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

public class ServletLogout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        System.out.println(logout);
        if(logout.equals("logout")){
//            Cookie[] cookies = req.getCookies();
//            CookieUtil cookieUtil = new CookieUtil();
//            Cookie cookieUser = Stream.of(cookies).filter(cookie -> cookieUtil.checkCookie(cookies)).findFirst().get();
//            cookieUser.setMaxAge(0);
//            resp.addCookie(cookieUser);
            new CookieUtil().killCookie(req.getCookies(), resp);
            resp.sendRedirect("/login");
        }else {
            resp.sendRedirect("/login");
        }
    }

}
