package tp.tp3.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp.tp3.metier.ImetierCatalogue;
import tp.tp3.metier.MetierImpl;
import tp.tp3.metier.Produit;

@WebServlet("/tp3")
public class ControleurServlet extends HttpServlet{
	
	private ImetierCatalogue metier;
	
	@Override
	public void init() throws ServletException{ 
		metier=new MetierImpl();
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProduitModele mod = new ProduitModele(); 
		List<Produit> prods = metier.getProduitsParMotCle(""); 
		mod.setProduits(prods);
		req.setAttribute("modele", mod);
		req.setAttribute("result",  mod);
		req.getRequestDispatcher("tp/tp3/ProduitsView.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mc=req.getParameter("motCle"); 
     	ProduitModele mod = new ProduitModele(); 
		mod.setMotCle(mc);
		List<Produit> prods = metier.getProduitsParMotCle(mc); 
		mod.setProduits(prods);
		req.setAttribute("modele", mod);
		req.getRequestDispatcher("tp/tp3/ProduitsView.jsp").forward(req,resp);
	}
}

/*
 *req.sendRedirect : une nouvelle requete , il ne connait pas le mot clé modele
 *req.getRequestDispatcher  :  La meme requete , il connait le mot clé modele
 * 
 */