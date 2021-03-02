package controleur;

public class Commander
{
	private int id_produit; 
	private int id_client;
	
	public Commander(int id_produit, int id_client)
	{
		this.id_produit = id_produit;
		this.id_client = id_client;
	}
	
	public Commander( int id_client)
	{
		this.id_produit = 0;
		this.id_client = id_client;
	}
	
	public Commander()
	{
		this.id_produit = 0;
		this.id_client = 0;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
}
