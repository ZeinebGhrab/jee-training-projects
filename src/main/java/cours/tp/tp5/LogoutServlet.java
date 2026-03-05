package cours.tp.tp5;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout_tp5")
public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Ne pas créer de nouvelle session si elle n'existe pas
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // Détruit la session
        }
        
        response.sendRedirect(request.getContextPath() + "/cours/tp/tp5/login.jsp");
    }
}