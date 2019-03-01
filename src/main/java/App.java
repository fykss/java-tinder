import dbConnection.DbConnection;
import filter.FilterLogin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.ServiceUsers;
import servlet.*;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) {
        Connection dbConn = new DbConnection().connection();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(ServletTemplates.class, "/src/main/resources/templates/css/*");
        handler.addServlet(new ServletHolder(new ServletLogin(dbConn)), "/login/*");
        handler.addServlet(new ServletHolder(new ServletUsers(dbConn)), "/users/*");
        handler.addServlet(new ServletHolder(new ServletPeopleList(dbConn)), "/liked/*");
        handler.addServlet(new ServletHolder(new ServletRegistration(dbConn)), "/reg/*");

        handler.addFilter(new FilterHolder(new FilterLogin()), "/users/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(handler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
