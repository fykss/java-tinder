package ua.com.danit.filter;

import ua.com.danit.service.ServiceUsers;
import ua.com.danit.utils.CookieUtil;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class FilterCookie implements Filter {

    private ServiceUsers serviceUsers;

    public FilterCookie(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        CookieUtil cookieUtil = new CookieUtil();

        if (req.getServletPath().equals("/logout")){
            serviceUsers.updateUserDate(cookieUtil.getIdUser(req.getCookies()));
            resp = cookieUtil.killCookie(req.getCookies(), resp);
            resp.sendRedirect("/login");
        } else{
            if (req.getServletPath().equals("/login") || req.getServletPath().equals("/reg") || req.getServletPath().equals("/templates/css")) {
                chain.doFilter(req, resp);
            } else {
                if (cookieUtil.checkCookie(req.getCookies())) {
                    chain.doFilter(req, resp);
                } else {
                    resp.sendRedirect("/login");
                }
            }
        }
    }
    @Override
    public void destroy() {}
}
