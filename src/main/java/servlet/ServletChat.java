package servlet;

import dto.Message;
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
import java.util.stream.Collectors;

public class ServletChat extends HttpServlet {

    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();
    private ServiceMessages serviceMessages;
    private ServiceUsers serviceUsers;

    public ServletChat(Connection dbConn) {
        this.serviceMessages = new ServiceMessages(dbConn);
        this.serviceUsers = new ServiceUsers(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUserSender = new CookieUtil().getIdUser(req.getCookies());
        int idUserRecipient = Integer.parseInt(req.getParameter("id"));

        data.put("conn", req.getRequestURI());
        data.put("user", serviceUsers.getUser(idUserRecipient));

        Collection<Message> allMessages = serviceMessages.getAllMessages(idUserSender, idUserRecipient);
        Collection<Message> allMessages2 = serviceMessages.getAllMessages(idUserRecipient, idUserSender);
        allMessages.addAll(allMessages2);
        List<Message> collect = allMessages.stream().sorted(Comparator.comparing(Message::getDate)).collect(Collectors.toList());

        data.put("idUser", idUserSender);
        data.put("listMsg", collect);

        Collection<User> allLikedUser = serviceUsers.getAllLikedUsers(idUserSender);
        allLikedUser.forEach(user -> user.setTimeDif(new DescribeTime().describeTimeDif((Date)user.getDate())));
        data.put("listUsers", allLikedUser);

        freemarker.render("people-list.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUserSender = new CookieUtil().getIdUser(req.getCookies());
        int idUserRecipient = Integer.parseInt(req.getParameter("id"));
        String messageText = req.getParameter("messageText");
        serviceMessages.addMessage(idUserSender,idUserRecipient,messageText);
        doGet(req, resp);
    }

}
