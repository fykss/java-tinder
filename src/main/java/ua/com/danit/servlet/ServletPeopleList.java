package ua.com.danit.servlet;

import ua.com.danit.dto.User;
import ua.com.danit.service.ServiceUsers;
import ua.com.danit.utils.CookieUtil;
import ua.com.danit.utils.CryptUtil;
import ua.com.danit.utils.DescribeTime;
import ua.com.danit.utils.Freemarker;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

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
            resp.setHeader("Refresh","3; URL=/users");
            freemarker.render("people-list_empty.ftl", data,resp);
        }else {
            allLikedUser.forEach(user -> user.setTimeDif(new DescribeTime().describeTimeDif((Date)user.getDate())));
            data.put("listUsers", allLikedUser);
            data.put("conn","");
            freemarker.render("people-list.ftl", data,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/liked/messages?id=" + CryptUtil.encryptExtra(req.getParameter("userId")));
    }

}
