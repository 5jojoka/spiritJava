package controleur;

public class Produit
{
	private int id_produit; 
	private String nom, tpsPreparation; 
	private float prix;
	
	
	public Produit(int id_produit, String nom, float prix, String tpsPreparation) {
		this.id_produit = id_produit;
		this.nom = nom;
		this.prix = prix;
		this.tpsPreparation = tpsPreparation;
	}

	public Produit(String nom, float prix, String tpsPreparation) {
		this.id_produit = 0;
		this.nom = nom;
		this.prix = prix;
		this.tpsPreparation = tpsPreparation;
	}
	
	public Produit() {
		this.id_produit = 0;
		this.nom = "";
		this.prix = 0;
		this.tpsPreparation = "";
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

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
}
