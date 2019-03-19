package ua.com.danit.servlet;

import ua.com.danit.dto.User;
import ua.com.danit.service.ServiceTempArrayListForUser;
import ua.com.danit.service.ServiceUsers;
import ua.com.danit.utils.CryptUtil;
import ua.com.danit.utils.Freemarker;
import ua.com.danit.utils.MailSender;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class ServletRegistration extends HttpServlet {

    private ServiceUsers serviceUsers;
    private ServiceTempArrayListForUser serviceTempArrayListForUser;
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();

    public ServletRegistration(Connection dbConn, ArrayList<User> tempArrayListForUsers) {
        this.serviceUsers = new ServiceUsers(dbConn);
        this.serviceTempArrayListForUser = new ServiceTempArrayListForUser(tempArrayListForUsers);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        freemarker.render("registration.html", data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String position = req.getParameter("position");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String urlImg = req.getParameter("urlImg");
        String gender = req.getParameter("gender");
        String textMessageMail = "<p>To activate your profile, follow the link below</p><br>";
        //remove if
        if(serviceUsers.checkEmail(email) || serviceTempArrayListForUser.checkEmailTemporaryStorage(email)){
            resp.setHeader("Refresh","3; URL=/reg");
            data.put("error_email", email);
            freemarker.render("reg_error.ftl", data,resp);
        }else {
            serviceTempArrayListForUser.addUser(name,surname,password,position,email,urlImg,gender);
            User user = serviceTempArrayListForUser.getUser(email);
            System.out.println(user);

            MailSender mailSender = new MailSender();
            mailSender.sendMessage(email, textMessageMail +
                        req.getRequestURL() + "/activate?em=" +
                        CryptUtil.encryptExtra(email));
            resp.setHeader("Refresh","3; URL=/login");
            data.put("email", email);
            freemarker.render("activate.ftl", data,resp);
        }
    }
}
