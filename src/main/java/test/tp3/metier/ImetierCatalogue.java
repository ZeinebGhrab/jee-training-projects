package test.tp3.metier;

import java.util.List;

public interface ImetierCatalogue {
    public List<Produit> getProduitsParMotCle(String mc);
    public void addProduit(Produit p);
    public void deleteProduit(Long id);
    public void updateProduit(Produit p);
    public Produit getProduit(Long id);
}
