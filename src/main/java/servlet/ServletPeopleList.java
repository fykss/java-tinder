package servlet;

import dto.LikeExtra;
import dto.User;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Collection;
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
        int idUser = new CookieUtil().getIdUser(req.getCookies());
//        Collection<LikeExtra> allLikeUser = serviceLikes.getAllLikeUser(idUser);
        Collection<User> allLikedUser = serviceUsers.getAllLikedUsers(idUser);
        allLikedUser.stream().forEach(user -> user.setTimeDif(describeTimeDif((Date)user.getDate())));
        data.put("listUsers", allLikedUser);
        freemarker.render("people-list.ftl", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/messages?id=" + req.getParameter("userId"));
    }

    private String describeTimeDif(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate timeLastin = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = LocalDate.now();
        Period period = Period.between(timeLastin, now);
        String result = "";
        if(period.getDays() == 0){
            result = "today";
        }else {
            result = period.getDays() + " days ago";
        }
        return result;
    }
}
