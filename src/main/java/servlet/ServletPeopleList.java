package servlet;

import service.ServiceLikes;
import service.ServiceUsers;
import utils.CookieUtil;
import utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class ServletPeopleList extends HttpServlet {

    private ServiceUsers serviceUsers;
    private ServiceLikes serviceLikes;
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();

    public ServletPeopleList(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
        this.serviceLikes = new ServiceLikes(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        freemarker.render("people-list.ftl", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = new CookieUtil().getIdUser(req.getCookies());



    }
}
