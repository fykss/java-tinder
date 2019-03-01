package servlet;

import dto.Message;
import dto.MessageExtra;
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
import java.util.Collection;
import java.util.HashMap;

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

        data.put("name", serviceUsers.getUser(idUserRecipient).getName());
        data.put("surname", serviceUsers.getUser(idUserRecipient).getSurname());
        data.put("urlImg", serviceUsers.getUser(idUserRecipient).getUrlImg());

        Collection<Message> allMessages = serviceMessages.getAllMessages(idUserSender, idUserRecipient);

        data.put("listMsg", allMessages);
        freemarker.render("chat.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
