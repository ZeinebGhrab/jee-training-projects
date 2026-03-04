package cours.chaptire3.Exemple1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "cs", urlPatterns = {"/fs"})
public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Récupérer le paramètre "age" depuis l'URL
        String ageParam = request.getParameter("age");

        // 2. Transmettre le paramètre à la JSP
        request.setAttribute("age", ageParam);

        // 3. Redirection vers View.jsp
        request.getRequestDispatcher("/cours/chapitre3/exemple1/View.jsp").forward(request, response);
    }
}