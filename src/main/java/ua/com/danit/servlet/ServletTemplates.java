package ua.com.danit.servlet;

import ua.com.danit.utils.Freemarker;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ServletTemplates extends HttpServlet {
    private Freemarker freemarker = new Freemarker();
    private HashMap<String, Object> data = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        freemarker.render("css"+req.getPathInfo(),data,resp);
    }
}
