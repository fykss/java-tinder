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
import java.sql.Timestamp;
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
        String gender = serviceUsers.getUser(idUserNotShow).getGender();

        User user = serviceUsers.getUser(countNext);
        if(user != null ){
            while (gender.equals(user.getGender())) {
                countNext++;
                user = serviceUsers.getUser(countNext);
                if(user == null ){
                    countNext = 1;
                    resp.sendRedirect("/liked");
                }
            }
            data.put("img", user.getUrlImg());
            data.put("name", user.getName());
            data.put("surname", user.getSurname());
            data.put("id", user.getId());
            freemarker.render("like-page.ftl", data,resp);
            countNext++;
        }else {
            countNext = 1;
            resp.sendRedirect("/liked");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dislike = req.getParameter("dislike");
        String like = req.getParameter("like");

        int idWho = new CookieUtil().getIdUser(req.getCookies());

        if(like != null){
            int idWhom = Integer.parseInt(like);
            Like likeWhom = serviceLikes.createLike(new Timestamp(System.currentTimeMillis()), idWho, idWhom);

            if(serviceLikes.checkLike(idWho, idWhom)){
                serviceLikes.updateLike(likeWhom);
                doGet(req,resp);
            }else{
                serviceLikes.saveLike(likeWhom);
                doGet(req,resp);
            }
        }else if(dislike != null){

            int idWhom = Integer.parseInt(dislike);

            if (serviceLikes.checkLike(idWho, idWhom)){
                serviceLikes.delLike(idWho, idWhom);
                doGet(req,resp);
            } else {
                doGet(req,resp);
            }
        }
    }
}
