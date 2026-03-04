package cours.chaptire2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name="Info_servlet", urlPatterns= {"/info"})
public class Application extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("Protocol: " + req.getProtocol());
        out.println("Scheme: " + req.getScheme());
        out.println("Server Name: " + req.getServerName());
        out.println("Server port: " + req.getServerPort());
        out.println("Methode: " + req.getMethod());
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}