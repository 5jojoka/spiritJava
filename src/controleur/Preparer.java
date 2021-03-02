package controleur;

public class Preparer
{
	private int id_produit;
	private int id_cuisinier;
	
	public Preparer(int id_produit, int id_cuisinier)
	{
		this.id_produit = id_produit;
		this.id_cuisinier = id_cuisinier;
	}
	
	public Preparer( int id_cuisinier)
	{
		this.id_produit = 0;
		this.id_cuisinier = id_cuisinier;
	}
	
	public Preparer()
	{
		this.id_produit = 0;
		this.id_cuisinier = 0;
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public int getId_cuisinier() {
		return id_cuisinier;
	}

	public void setId_cuisinier(int id_cuisinier) {
		this.id_cuisinier = id_cuisinier;
	}

}
