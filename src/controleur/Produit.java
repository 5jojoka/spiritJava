package controleur;

public class Produit
{
	private int id_produit; 
	private String nom, tpsPreparation; 
	private int prix;
	
	
	public Produit(int id_produit, String nom, String tpsPreparation, int prix) {
		this.id_produit = id_produit;
		this.nom = nom;
		this.tpsPreparation = tpsPreparation;
		this.prix = prix;
	}

	public Produit(String nom, int prix, String tpsPreparation) {
		this.id_produit = 0;
		this.nom = nom;
		this.tpsPreparation = tpsPreparation;
		this.prix = prix;
	}
	
	public Produit() {
		this.id_produit = 0;
		this.nom = "";
		this.tpsPreparation = "";
		this.prix = 0;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTpsPreparation() {
		return tpsPreparation;
	}

	public void setTpsPreparation(String tpsPreparation) {
		this.tpsPreparation = tpsPreparation;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
}