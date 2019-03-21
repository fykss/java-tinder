package ua.com.danit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.com.danit.dbConnection.DbConnection;
import ua.com.danit.dto.User;
import ua.com.danit.filter.FilterCookie;
import ua.com.danit.servlet.*;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) {
        Connection dbConn = new DbConnection().connection();
        ServletContextHandler handler = new ServletContextHandler();
        ArrayList<User> tempArrayListForUsers = new ArrayList<>();

        handler.addServlet(ServletTemplates.class, "/templates/css/*");
        handler.addServlet(new ServletHolder(new ServletRegistration(dbConn, tempArrayListForUsers)), "/reg");
        handler.addServlet(new ServletHolder(new ServletLogin(dbConn)), "/login/*");
        handler.addServlet(new ServletHolder(new ServletUsers(dbConn)), "/users/*");
        handler.addServlet(new ServletHolder(new ServletPeopleList(dbConn)), "/liked");
        handler.addServlet(new ServletHolder(new ServletChat(dbConn)), "/liked/*");
        handler.addServlet(new ServletHolder(new ServletActivate(dbConn, tempArrayListForUsers)), "/reg/activate");
        handler.addFilter(new FilterHolder(new FilterCookie(dbConn)), "/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        String port = System.getenv().get("PORT");
        if (port == null || port.equals("")){
            port="8080";
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

