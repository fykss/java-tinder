package ua.com.danit.servlet;

import ua.com.danit.dto.User;
import ua.com.danit.service.ServiceTempArrayListForUser;
import ua.com.danit.service.ServiceUsers;
import ua.com.danit.utils.CryptUtil;
import ua.com.danit.utils.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class ServletActivate extends HttpServlet {

    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();
    private ServiceUsers serviceUsers;
    private ServiceTempArrayListForUser serviceTempArrayListForUser;

    public ServletActivate(Connection dbConn, ServiceTempArrayListForUser tempArrayListForUsers) {
        this.serviceUsers = new ServiceUsers(dbConn);
        this.serviceTempArrayListForUser = tempArrayListForUsers;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = CryptUtil.decryptExtra(req.getParameter("em"));
        if(serviceTempArrayListForUser.getAllUsers().isEmpty() || serviceTempArrayListForUser.getUser(email) == null){
            resp.setHeader("Refresh","3; URL=/reg");
            freemarker.render("error_active.ftl", data,resp);
        }else {
            if(serviceTempArrayListForUser.timeActive(email)){
                resp.setHeader("Refresh","3; URL=/reg");
                freemarker.render("error_active.ftl", data,resp);
                serviceTempArrayListForUser.delUser(email);
            }else {
                resp.setHeader("Refresh","3; URL=/login");
                User user = serviceTempArrayListForUser.getUser(email);
                serviceUsers.addUser(
                        user.getName(),
                        user.getSurname(),
                        user.getPassword(),
                        user.getPosition(),
                        user.getEmail(),
                        user.getUrlImg(),
                        user.getGender());
                serviceTempArrayListForUser.delUser(email);
                freemarker.render("reg_success.ftl", data,resp);
            }
        }

    }
}
