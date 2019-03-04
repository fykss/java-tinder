package servlet;

import dto.Message;
import dto.User;
import service.ServiceMessages;
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
import java.util.*;
import java.util.stream.Collectors;

public class ServletPeopleList extends HttpServlet {

    private ServiceUsers serviceUsers;
    private ServiceMessages serviceMessages;
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();

    public ServletPeopleList(Connection dbConn) {

        this.serviceUsers = new ServiceUsers(dbConn);
        this.serviceMessages = new ServiceMessages(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = new CookieUtil().getIdUser(req.getCookies());
        Collection<User> allLikedUser = serviceUsers.getAllLikedUsers(idUser);
        if(allLikedUser.size() == 0){
            resp.sendRedirect("/users");
        }
        allLikedUser.forEach(user -> user.setTimeDif(describeTimeDif((Date)user.getDate())));
        data.put("listUsers", allLikedUser);

        String requestURI = req.getRequestURI();
        data.put("conn", requestURI);

        int idUserSender = new CookieUtil().getIdUser(req.getCookies());
        String userId = req.getParameter("userId");
        if(userId != null){
            int idUserRecipient = Integer.parseInt(userId);

            data.put("idRecipient", idUserRecipient);
            data.put("name", serviceUsers.getUser(idUserRecipient).getName());
            data.put("surname", serviceUsers.getUser(idUserRecipient).getSurname());
            data.put("urlImg", serviceUsers.getUser(idUserRecipient).getUrlImg());

            Collection<Message> allMessages = serviceMessages.getAllMessages(idUserSender, idUserRecipient);
            Collection<Message> allMessages2 = serviceMessages.getAllMessages(idUserRecipient, idUserSender);
            allMessages.addAll(allMessages2);
            List<Message> collect = allMessages.stream().sorted(Comparator.comparing(Message::getDate)).collect(Collectors.toList());
            data.put("idUser", idUserSender);
            data.put("listMsg", collect);

            freemarker.render("people-list.ftl", data,resp);
        }
        freemarker.render("people-list.ftl", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/liked/messages?id=" + req.getParameter("userId"));

//        int idUserSender = new CookieUtil().getIdUser(req.getCookies());
//        int idUserRecipient = Integer.parseInt(req.getParameter("id"));
//        String messageText = req.getParameter("messageText");
//        serviceMessages.addMessage(idUserSender,idUserRecipient,messageText);

        doGet(req,resp);

//        serviceMessages.addMessage(idUserSender,idUserRecipient,messageText);
//        doGet(req, resp);

//        int idUserSender = new CookieUtil().getIdUser(req.getCookies());
//        int idUserRecipient = Integer.parseInt(req.getParameter("userId"));
//        data.put("idRecipient", idUserRecipient);
//        data.put("name", serviceUsers.getUser(idUserRecipient).getName());
//        data.put("surname", serviceUsers.getUser(idUserRecipient).getSurname());
//        data.put("urlImg", serviceUsers.getUser(idUserRecipient).getUrlImg());
//
//        Collection<Message> allMessages = serviceMessages.getAllMessages(idUserSender, idUserRecipient);
//        Collection<Message> allMessages2 = serviceMessages.getAllMessages(idUserRecipient, idUserSender);
//        allMessages.addAll(allMessages2);
//        List<Message> collect = allMessages.stream().sorted(Comparator.comparing(Message::getDate)).collect(Collectors.toList());
//        data.put("idUser", idUserSender);
//        data.put("listMsg", collect);
//
//        freemarker.render("people-list.ftl",data,resp);
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
