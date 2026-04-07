package test.tp3.web;

import java.util.ArrayList;
import java.util.List;
import test.tp3.metier.Produit;

public class ProduitModele {
    private String motCle;
    private Long idProduit; // Pour l'édition
    private String action;  // Pour gérer l'action (add, edit, delete)
    private List<Produit> produits = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}