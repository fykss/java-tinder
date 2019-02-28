package filter;

import utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterLogin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        CookieUtil cookieUtil = new CookieUtil();
        if(cookieUtil.checkCookie(req.getCookies())){
            chain.doFilter(req, resp);
        }else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {}
}
