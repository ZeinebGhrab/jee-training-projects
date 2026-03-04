package cours.tp.tp7.web;

import java.io.IOException;
import cours.tp.tp7.metier.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    private UserDAO dao = new UserDAO();

    // Gestion des requêtes GET (affichage du formulaire)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/cours/tp/tp7/login.jsp").forward(request, response);
    }

    // Gestion des requêtes POST (traitement de l'authentification)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (dao.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            // Redirection vers la servlet user-list
            response.sendRedirect(request.getContextPath() + "/user-list");
        } else {
            request.setAttribute("error", "Identifiants invalides");
            // Forward vers le JSP avec le chemin correct
            request.getRequestDispatcher("/cours/tp/tp7/login.jsp").forward(request, response);
        }
    }
}