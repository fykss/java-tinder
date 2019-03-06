package ua.com.danit.servlet;

import ua.com.danit.service.ServiceUsers;
import ua.com.danit.utils.CookieUtil;
import ua.com.danit.utils.Freemarker;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class ServletLogin extends HttpServlet {

    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();
    private ServiceUsers serviceUsers;

    public ServletLogin(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        freemarker.render("login.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int idUser = serviceUsers.getIdUser(email, password);

        if(idUser>0){
            resp = new CookieUtil().addCookie("tinderUser", Integer.toString(idUser), resp);
            serviceUsers.updateUserDate(idUser);
            resp.sendRedirect("/users");
        }else {
            resp.setHeader("Refresh","3; URL=/login");
            data.put("error", email);
            freemarker.render("login_error.ftl",data,resp);
        }
    }
}
