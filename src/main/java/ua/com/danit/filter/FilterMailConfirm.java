package ua.com.danit.filter;

import ua.com.danit.service.ServiceTempArrayListForUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterMailConfirm implements Filter {

    private ServiceTempArrayListForUser users;
    public FilterMailConfirm(ServiceTempArrayListForUser users) {
        this.users = users;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getMethod().equalsIgnoreCase("get")){
            users.getAllUsers().removeIf(user -> users.timeActive(user.getEmail()));
        }
        chain.doFilter(req, resp);


    }

    @Override
    public void destroy() {

    }
}
