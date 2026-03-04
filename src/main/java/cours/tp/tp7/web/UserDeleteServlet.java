package cours.tp.tp7.web;  // ← IMPORTANT : même package que les autres servlets

import java.io.IOException;
import cours.tp.tp7.metier.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;  // ← Import de l'annotation
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet pour supprimer un utilisateur.
 * Mapping : /user-delete
 * Méthode : GET (via lien) ou POST (via formulaire)
 */
@WebServlet("/user-delete")  // ← CRITIQUE : mapping exact de l'URL
public class UserDeleteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("[Servlet] === GET /user-delete ===");
        
        String idParam = request.getParameter("id");
        System.out.println("[Servlet] Delete request - id param: '" + idParam + "'");
        
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam.trim());
                System.out.println("[Servlet] Parsed id: " + id);
                
                boolean deleted = dao.deleteUser(id);
                System.out.println("[Servlet] Delete result: " + 
                    (deleted ? "✅ SUCCESS" : "❌ FAILED - user not found?"));
                
            } catch (NumberFormatException e) {
                System.err.println("[Servlet] ❌ Invalid id format: '" + idParam + "'");
            }
        } else {
            System.err.println("[Servlet] ❌ Missing or empty id parameter");
        }
        
        // ✅ Redirection vers la liste (toujours, même en cas d'erreur)
        response.sendRedirect(request.getContextPath() + "/user-list");
    }
    
    // ✅ Accepter aussi POST pour plus de flexibilité
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}