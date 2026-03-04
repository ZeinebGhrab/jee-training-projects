package cours.chaptire3.Exemple2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des paramètres du formulaire
        String titre = request.getParameter("titre");
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String ageStr = request.getParameter("age");

        // Stockage dans les attributs pour la JSP
        request.setAttribute("titre", titre);
        request.setAttribute("prenom", prenom);
        request.setAttribute("nom", nom);
        request.setAttribute("age", ageStr);

        // Traitement du message selon l'âge
        String message = "Vous êtes un";
        if (ageStr != null && !ageStr.isEmpty()) {
	        int age = Integer.parseInt(ageStr);
	        message = "Vous êtes un " + PersonneService.getTypeParAge(age) + ".";
	    }
        request.setAttribute("message", message);

        // Redirection vers la vue
        request.getRequestDispatcher("/cours/chapitre3/exemple2/resultat.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Afficher le formulaire
        request.getRequestDispatcher("/cours/chapitre3/exemple2/formulaire.jsp").forward(request, response);
    }
}