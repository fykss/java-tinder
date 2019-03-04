package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServletTemplates extends HttpServlet {
    private final String TEMPLATE_ROOT = "./target/classes/templates/css/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get(TEMPLATE_ROOT, req.getPathInfo()), resp.getOutputStream());
        this.getClass().getClassLoader().getResource("/target/classes/templates/css");
    }
}
