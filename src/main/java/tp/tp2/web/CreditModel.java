package tp.tp2.web;

public class CreditModel {
	
	private float montant;
	private int duree; 
	private float taux;
	private double mensualite;

	
	public CreditModel () {}
	
	
	public CreditModel(float montant, int duree, float taux, double mensualite) {
		super();
		this.montant = montant;
		this.duree = duree;
		this.taux = taux;
		this.mensualite = mensualite;
	}
	
	public float getMontant() {
		return montant;
	}
	
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public float getTaux() {
		return taux;
	}
	
	public void setTaux(float taux) {
		this.taux = taux;
	}
	
	public double getMensualite() {
		return mensualite;
	}
	
	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}
	
}
