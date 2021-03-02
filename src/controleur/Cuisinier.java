package controleur;

public class Cuisinier
{
	private int id_cuisinier; 
	private String nom, prenom, adresse, tel, mail;
	
	public Cuisinier(int id_cuisinier, String nom, String prenom, String adresse, String tel, String mail)
	{
		super();
		this.id_cuisinier = id_cuisinier;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
	} 	
	
	public Cuisinier(String nom, String prenom, String adresse, String tel, String mail)
	{
		super();
		this.id_cuisinier = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
	} 

	public Cuisinier()
	{
		super();
		this.id_cuisinier = 0;
		this.nom = "";
		this.prenom = "";
		this.adresse = "";
		this.tel = "";
		this.mail = "";
	}

	public int getId_cuisinier() {
		return this.id_cuisinier;
	}

	public void setId_cuisinier(int id_cuisinier) {
		this.id_cuisinier = id_cuisinier;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
