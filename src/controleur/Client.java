package controleur;

public class Client
{
	private int id_client; 
	private String nom, prenom, adresse, tel,mail;
	
	public Client(int id_client, String nom, String prenom, String adresse, String tel, String mail)
	{
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
	}
	
	public Client(String nom, String prenom, String adresse, String tel, String mail)
	{
		super();
		this.id_client = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
	}
	
	public Client()
	{
		this.id_client = 0;
		this.nom = "";
		this.prenom = "";
		this.adresse = "";
		this.tel = "";
		this.mail = "";
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
