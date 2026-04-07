package tp.tp3.web;

import java.util.ArrayList;
import java.util.List;
import tp.tp3.metier.Produit;

public class ProduitModele {
    private String motCle;
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
}