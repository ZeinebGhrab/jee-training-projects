package tp.tp2.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.tp2.metier.CreditMetierImp;


@WebServlet("/calcul")
public class ControleurServlet extends HttpServlet {
	
	private CreditMetierImp metier ;
	
	public void init() throws ServletException {
        metier = new CreditMetierImp();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/tp/tp2/VueCredit.jsp").forward(req, resp);
		req.setAttribute("result", new CreditModel());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		float montant = Float.parseFloat(req.getParameter("montant"));
		int duree = Integer.parseInt(req.getParameter("duree"));
		float taux = Float.parseFloat(req.getParameter("taux"));
		  
		CreditModel credit  = new CreditModel();
		
		credit.setMontant(montant);
		credit.setDuree(duree);
		credit.setTaux(taux);
		
		credit.setMensualite(metier.calculeMensu(montant, taux, duree));
		
		req.setAttribute("result", credit);
		
		req.getRequestDispatcher("/tp/tp2/VueCredit.jsp").forward(req, resp);
	}
}
