package cours.tp.tp5;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login_tp5")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        
        // Validation basique
        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect("cours/tp/tp5/login.jsp?error=empty");
            return;
        }
        
        // Création/récupération de session
        HttpSession session = request.getSession(true);
        session.setAttribute("user", username.trim());
        
        // Redirection vers page d'accueil
        response.sendRedirect(request.getContextPath() + "/cours/tp/tp5/welcome.jsp");
    }
}