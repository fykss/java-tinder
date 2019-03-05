package ua.com.danit.servlet;

import ua.com.danit.dto.User;
import ua.com.danit.service.ServiceLikes;
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

public class ServletUsers extends HttpServlet {

    private ServiceUsers serviceUsers;
    private ServiceLikes serviceLikes;
    private Freemarker freemarker = new Freemarker();
    private int countNext = 1;
    private int idUserFromCookie;

    public ServletUsers(Connection dbConn) {
        this.serviceUsers = new ServiceUsers(dbConn);
        this.serviceLikes = new ServiceLikes(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idUserFromCookie = new CookieUtil().getIdUser(req.getCookies());
        String gender = serviceUsers.getUser(idUserFromCookie).getGender();
        HashMap<String, Object> data = new HashMap<>();

        //забрати іфи!!! переробити counter
        User user = serviceUsers.getUser(countNext);
        if(user != null ){
            while (gender.equals(user.getGender())) {
                if(user != null ){
                    countNext++;
                    user = serviceUsers.getUser(countNext);
                }else {
                    countNext = 1;
                    resp.sendRedirect("/liked");
                }
            }
            data.put("user", user);
            freemarker.render("like-page.ftl", data, resp);
            countNext++;
        }else {
            countNext = 1;
            resp.sendRedirect("/liked");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idUserFromCookie = new CookieUtil().getIdUser(req.getCookies());
        String dislike = req.getParameter("dislike");
        String like = req.getParameter("like");
        int userIdWhom;

        //забрати іфи!!!
        if(like != null){
            userIdWhom = Integer.parseInt(like);
            if(serviceLikes.checkLike(idUserFromCookie, userIdWhom)){
                doGet(req,resp);
            }else{
                serviceLikes.saveLike(idUserFromCookie, userIdWhom);
                doGet(req,resp);
            }
        }else if(dislike != null){
            userIdWhom = Integer.parseInt(dislike);
            if (serviceLikes.checkLike(idUserFromCookie, userIdWhom)){
                serviceLikes.delLike(idUserFromCookie, userIdWhom);
                doGet(req,resp);
            } else {
                doGet(req,resp);
            }
        }
    }
}
