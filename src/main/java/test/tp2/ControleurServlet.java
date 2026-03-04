package test.tp2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/calcul_tp2")
public class ControleurServlet extends HttpServlet {
	
	private ICreditMetier metier;
	
	public void init() throws ServletException {
	        metier = new CreditMetierImp();
	    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/test/tp2/VueCredit.jsp").forward(request,response);
    }
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	        // 1. Lecture des données
	        double montant = Double.parseDouble(request.getParameter("montant"));
	        double taux = Double.parseDouble(request.getParameter("taux"));
	        int duree = Integer.parseInt(request.getParameter("duree"));
	        
	        // 2. Instanciation du modèle
	        CreditModel model = new CreditModel();
	        
	        // 3. Data binding
	        model.setMontant(montant);
	        model.setTaux(taux);
	        model.setDuree(duree);
	        
	        // 4. Appel à la couche métier
	        double mensualite = metier.calculeMensu(montant, taux, duree);
	        
	        // 5. Stockage du résultat dans le modèle
	        model.setMensualite(mensualite);
	        
	        // 6. Stockage du modèle dans la requête
	        request.setAttribute("resultat", model);
	        
	        // 7. Forward vers la vue
	        request.getRequestDispatcher("/test/tp2/VueCredit.jsp").forward(request, response);
	    }
}
