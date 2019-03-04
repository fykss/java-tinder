package filter;

import utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterCookie implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();


//        String logout = req.getParameter("logout");
        if (req.getServletPath().equals("/logout")){
            resp = new CookieUtil().killCookie(req.getCookies(), resp);
            resp.sendRedirect("/login");
        } else{
            if (req.getServletPath().equals("/login") || req.getServletPath().equals("/reg") || req.getServletPath().equals("/target/classes/templates/css")) {
                chain.doFilter(req, resp);
            } else {
                if (cookies != null && cookies.length != 0) {
                    for (Cookie c : cookies) {
                        if (c.getName().equals("tinderUser")) {
                            chain.doFilter(req, resp);
                        } else {
                            resp.sendRedirect("/login");
                        }
                    }
                } else {
                    resp.sendRedirect("/login");
                }
            }
        }



    }
    @Override
    public void destroy() {}
}
