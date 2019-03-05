package servlet;

import dto.User;
import service.ServiceMessages;
import service.ServiceUsers;
import utils.CookieUtil;
import utils.DescribeTime;
import utils.Freemarker;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;

public class ServletPeopleList extends HttpServlet {

    private ServiceUsers serviceUsers;
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();

    public ServletPeopleList(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = new CookieUtil().getIdUser(req.getCookies());
        Collection<User> allLikedUser = serviceUsers.getAllLikedUsers(idUser);
        if(allLikedUser.size() == 0){
            resp.sendRedirect("/users");
        }
        allLikedUser.forEach(user -> user.setTimeDif(new DescribeTime().describeTimeDif((Date)user.getDate())));
        data.put("listUsers", allLikedUser);
        data.put("conn","");
        freemarker.render("people-list.ftl", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/liked/messages?id=" + req.getParameter("userId"));
    }

}
