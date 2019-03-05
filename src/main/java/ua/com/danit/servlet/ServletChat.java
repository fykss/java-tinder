package ua.com.danit.servlet;

import ua.com.danit.dto.Message;
import ua.com.danit.service.ServiceMessages;
import ua.com.danit.service.ServiceUsers;
import ua.com.danit.utils.CookieUtil;
import ua.com.danit.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
        freemarker.render("chat.ftl",data,resp);
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
