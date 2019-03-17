package ua.com.danit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.com.danit.dbConnection.DbConnection;
import ua.com.danit.filter.FilterCookie;
import ua.com.danit.servlet.*;
import ua.com.danit.utils.Freemarker;
import ua.com.danit.utils.MailSender;

import javax.mail.MessagingException;
import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Connection dbConn = new DbConnection().connection();
        ServletContextHandler handler = new ServletContextHandler();
        Freemarker freemarker = new Freemarker();
        HashMap<String, Object> data = new HashMap<>();

        try {
            MailSender mailSender = new MailSender();
            mailSender.sendMessage("nazden90@gmail.com", "");
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        handler.addServlet(ServletTemplates.class, "/templates/css/*");
        handler.addServlet(new ServletHolder(new ServletRegistration(dbConn)), "/reg/*");
        handler.addServlet(new ServletHolder(new ServletLogin(dbConn)), "/login/*");
        handler.addServlet(new ServletHolder(new ServletUsers(dbConn)), "/users/*");
        handler.addServlet(new ServletHolder(new ServletPeopleList(dbConn)), "/liked");
        handler.addServlet(new ServletHolder(new ServletChat(dbConn)), "/liked/*");
        handler.addFilter(new FilterHolder(new FilterCookie(dbConn)), "/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

        String port = System.getenv().get("PORT");
        if (port == null || port.equals("")){
            port="8082";
        }

        Server server = new Server(Integer.parseInt(port));
        server.setHandler(handler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
