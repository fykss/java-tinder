package servlet;

import service.ServiceUsers;
import utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class ServletRegistration extends HttpServlet {

    private ServiceUsers serviceUsers;
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();

    public ServletRegistration(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        freemarker.render("registration.html", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String position = req.getParameter("position");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("passwordConfirm");
        String urlImg = req.getParameter("urlImg");
        String gender = req.getParameter("gender");

        System.out.println(name);
        System.out.println(surname);
        System.out.println(position);
        System.out.println(email);
        System.out.println(password);
        System.out.println(passwordConfirm);
        System.out.println(urlImg);
        System.out.println(gender);

        freemarker.render("registration.html", data,resp);
    }
}
