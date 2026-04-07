package test.tp3.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import test.tp3.metier.ImetierCatalogue;
import test.tp3.metier.MetierImpl;
import test.tp3.metier.Produit;

@WebServlet("/test_tp3")
public class ControleurServlet extends HttpServlet {

    private ImetierCatalogue metier;

    @Override
    public void init() throws ServletException {
        metier = new MetierImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "liste";
        }

        ProduitModele mod = new ProduitModele();

        switch (action) {

            case "liste":
                String mc = request.getParameter("motCle");
                if (mc == null) mc = "";
                mod.setMotCle(mc);
                List<Produit> prods = metier.getProduitsParMotCle(mc);
                mod.setProduits(prods);
                break;

            case "supprimer":
                Long idSup = Long.parseLong(request.getParameter("id"));
                metier.deleteProduit(idSup);
                response.sendRedirect(request.getContextPath() + "/test_tp3?action=liste");
                return;

            case "edit":
                Long idEdit = Long.parseLong(request.getParameter("id"));
                Produit p = metier.getProduit(idEdit);
                mod.setIdProduit(p.getIdProduit());
                mod.setMotCle(p.getNomProduit());
                mod.setProduits(metier.getProduitsParMotCle(""));
                break;
        }

        request.setAttribute("modele", mod);

        // ⚠️ Vérifie que ton JSP est bien ici :
        // WebContent/test/tp3/ProduitsView.jsp
        request.getRequestDispatcher("/test/tp3/ProduitsView.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/test_tp3?action=liste");
            return;
        }

        ProduitModele mod = new ProduitModele();

        switch (action) {

            case "search":
                String mc = request.getParameter("motCle");
                mod.setMotCle(mc);
                mod.setProduits(metier.getProduitsParMotCle(mc));
                break;

            case "ajouter":
                String nomAjout = request.getParameter("nom");
                double prixAjout = Double.parseDouble(request.getParameter("prix"));
                metier.addProduit(new Produit(nomAjout, prixAjout));
                response.sendRedirect(request.getContextPath() + "/test_tp3?action=liste");
                return;

            case "update":
                Long idUpdate = Long.parseLong(request.getParameter("id"));
                String nomUpdate = request.getParameter("nom");
                double prixUpdate = Double.parseDouble(request.getParameter("prix"));

                Produit produitUpdate = new Produit(nomUpdate, prixUpdate);
                produitUpdate.setIdProduit(idUpdate);

                metier.updateProduit(produitUpdate);
                response.sendRedirect(request.getContextPath() + "/test_tp3?action=liste");
                return;
        }

        request.setAttribute("modele", mod);
        request.getRequestDispatcher("/test/tp3/ProduitsView.jsp")
               .forward(request, response);
    }
}