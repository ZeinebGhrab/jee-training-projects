package cours.tp.tp7.web;

import java.io.IOException;
import java.util.List;
import cours.tp.tp7.metier.User;
import cours.tp.tp7.metier.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {
    
    private UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Sécurité : Vérification de la session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<User> users = dao.getAllUsers();
        request.setAttribute("users", users);
        // Forward vers le JSP avec le chemin correct
        request.getRequestDispatcher("/cours/tp/tp7/user-list.jsp").forward(request, response);
    }
}