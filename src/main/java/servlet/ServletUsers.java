package servlet;

import dto.Like;
import dto.User;
import utils.CookieUtil;
import utils.Freemarker;
import service.ServiceLikes;
import service.ServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;

public class ServletUsers extends HttpServlet {

    private ServiceUsers serviceUsers;
    private ServiceLikes serviceLikes;
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();
    private int countNext = 1;

    public ServletUsers(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
        this.serviceLikes = new ServiceLikes(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUserNotShow = new CookieUtil().getIdUser(req.getCookies());
        if(countNext == idUserNotShow) {
            countNext += 1;
        }
        User user = serviceUsers.getUser(countNext);
        if(user == null ){
            resp.sendRedirect("/liked");
        }else {
            data.put("img", user.getUrlImg());
            data.put("name", user.getName());
            data.put("surname", user.getSurname());
            data.put("id", user.getId());
            freemarker.render("like-page.ftl", data,resp);
            countNext++;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dislike = req.getParameter("dislike");
        String like = req.getParameter("like");

        if(like != null){
            int IdWho = new CookieUtil().getIdUser(req.getCookies());
            int idWhom = Integer.parseInt(like);

            Like likeWhom = serviceLikes.createLike(new Date(System.currentTimeMillis()), IdWho, idWhom);
            serviceLikes.saveLike(likeWhom);
            doGet(req,resp);
        }else if(dislike != null){
            doGet(req,resp);
        }
    }
}
