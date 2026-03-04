package cours.tp.tp7.web;

import java.io.IOException;
import cours.tp.tp7.metier.User;
import cours.tp.tp7.metier.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user-form")
public class UserFormServlet extends HttpServlet {
    
    private UserDAO dao = new UserDAO();

    private int parseInt(String value, int defaultValue) {
        if (value == null || value.trim().isEmpty()) return defaultValue;
        try { return Integer.parseInt(value.trim()); } 
        catch (NumberFormatException e) { return defaultValue; }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        int id = parseInt(request.getParameter("id"), -1);
        
  
        if ("edit".equals(action) && id > 0) {
            User user = dao.getUserById(id);
            if (user != null) {
                request.setAttribute("editUser", user);  
            }
        }
        request.getRequestDispatcher("/cours/tp/tp7/user-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = parseInt(request.getParameter("id"), 0);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont requis");
            request.getRequestDispatcher("/cours/tp/tp7/user-form.jsp").forward(request, response);
            return;
        }
        
        User user = new User(id, username.trim(), password.trim());
        
        if (id == 0) {
            dao.addUser(user);
        } else {
            dao.updateUser(user);
        }
        response.sendRedirect(request.getContextPath() + "/user-list");
    }
}