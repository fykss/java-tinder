package servlet;

import utils.CookieUtil;
import utils.Freemarker;
import service.ServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Files.copy(Paths.get("src/main/resources/templates/login.ftl"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
//        Boolean prmExists = Stream.of(req.getParameterMap()).map(stringMap -> {
//            String[] emails = stringMap.get("email");
//            String email = Stream.of(emails).findFirst().get();
//
//            String[] passwords = stringMap.get("password");
//            String password = Stream.of(passwords).findFirst().get();
//
//            return serviceUsers.checkUser(email, password);
//        }).findFirst().get();
        if(serviceUsers.checkUser(email,password)){
            int idUser = serviceUsers.getIdUser(email, password);
            Cookie cookie = new CookieUtil().addCookie("tinderUser", Integer.toString(idUser));
            resp.addCookie(cookie);
            resp.sendRedirect("/users");
            serviceUsers.updateUserDate(idUser);
        }else {
            resp.setHeader("Refresh","3; URL=/login");
            data.put("error", email);
            freemarker.render("login_error.ftl",data,resp);
        }
    }
}
