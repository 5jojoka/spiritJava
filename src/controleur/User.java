package controleur;

public class User {
	private int iduser;
	private String email, mdp, nom, prenom, droits;
	
	public User(int iduser, String email, String mdp, String nom, String prenom, String droits) {
		this.iduser = iduser;
		this.email = email;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.droits = droits;
	}
	
	public User(String email, String mdp, String nom, String prenom, String droits) {
		this.iduser = 0;
		this.email = email;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.droits = droits;
	}
	
	public User() {
		this.iduser = 0;
		this.email = "";
		this.mdp = "";
		this.nom = "";
		this.prenom = "";
		this.droits = "";
	}

	public int getIduser() {
		return iduser;
	}

	public String getEmail() {
		return email;
	}

	public String getMdp() {
		return mdp;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getDroits() {
		return droits;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setDroits(String droits) {
		this.droits = droits;
	}
}
