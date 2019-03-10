package ua.com.danit.servlet;

import ua.com.danit.dto.Message;
import ua.com.danit.dto.User;
import ua.com.danit.service.ServiceMessages;
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

public class ServletChat extends HttpServlet {

    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();
    private ServiceMessages serviceMessages;
    private ServiceUsers serviceUsers;
    private int idUserFromCookie;

    public ServletChat(Connection dbConn) {
        this.serviceMessages = new ServiceMessages(dbConn);
        this.serviceUsers = new ServiceUsers(dbConn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idUserFromCookie = new CookieUtil().getIdUser(req.getCookies());
        int idUserRecipient = Integer.parseInt(CryptUtil.decryptExtra(req.getParameter("id")));

        data.put("idUser", idUserFromCookie);
        data.put("conn", req.getRequestURI());
        data.put("user",serviceUsers.getUser(idUserRecipient));
        data.put("userId", CryptUtil.encryptExtra(Integer.toString(serviceUsers.getUser(idUserRecipient).getId())));

        Collection<Message> allMessages = serviceMessages.getAllMessages(idUserFromCookie, idUserRecipient);
        data.put("listMsg", allMessages);

        Collection<User> allLikedUser = serviceUsers.getAllLikedUsers(idUserFromCookie);
        allLikedUser.forEach(user -> user.setTimeDif(new DescribeTime().describeTimeDif((Date)user.getDate())));
        data.put("listUsers", allLikedUser);

        freemarker.render("people-list.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUserSender = new CookieUtil().getIdUser(req.getCookies());
        int idUserRecipient = Integer.parseInt(CryptUtil.decryptExtra(req.getParameter("id")));
        String messageText = req.getParameter("messageText");
        serviceMessages.addMessage(idUserSender,idUserRecipient,messageText);
        doGet(req, resp);
    }



}
