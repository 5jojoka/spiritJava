package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Cuisinier;
import controleur.Produit;
import controleur.User;

public class Modele 
{
	private static Bdd uneBdd = new Bdd ("localhost","spiritJava","root","");
	
	// pour PC Mac : private static Bdd uneBdd = new Bdd ("localhost:8889","airfrance","root","root");
	
	public static User verifConnexion (String email, String mdp) 
	{
		User unUser = null; 
		String requete = "select * from user where email = '" + email +"'  and mdp ='" + mdp + "' ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete); 
			
			if (unRes.next()) {
				unUser = new User (
						unRes.getInt("iduser"), unRes.getString("email"), unRes.getString("mdp"), 
						unRes.getString("nom"), unRes.getString("prenom"), unRes.getString("droits")
						);
			}
			unRes.close();
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		
		return unUser; 
	}
	
	//methode gï¿½nï¿½rique d'exï¿½cution de n'importe quelle requete nï¿½cessitant pas un retour de rï¿½sultats 
	public static void executerRequete (String requete)
	{
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
	}
	
	
	// ############################################################ PRODUIT ############################################################
	
	
	public static void insertProduit (Produit unProduit)
	{
		String requete ="insert into produit values (null, '" + unProduit.getNom() + "','" + unProduit.getPrix()
		+"','" + unProduit.getTpsPreparation() + "' );" ;
		executerRequete(requete);
	}
	
	public static void deleteProduit (int id_produit)
	{
		String requete =" delete from produit where id_produit = " + id_produit +" ; " ;
		executerRequete(requete);
	}
	
	public static void updateProduit (Produit unProduit)
	{
		String requete ="update produit set nom = '" + unProduit.getNom() + "', prix = '" + unProduit.getPrix()
		+"', tpsPreparation = '" + unProduit.getTpsPreparation() + "' ;" ;
		executerRequete(requete);
	}
	
	public static Produit selectWhereProduit (int id_produit)
	{
		String requete ="select * from produit where id_produit = "+ id_produit +";" ;
		Produit unProduit = null ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unProduit = new Produit
						(unRes.getInt("id_produit"), unRes.getString("nom"), unRes.getFloat("prix"),
								unRes.getString("tpsPreparation"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		return unProduit ; 
	}
	
	//surcharge de la methode avec de nouveaux arguments
	public static Produit selectWhereProduit (String nom, Float prix)
	{
		String requete ="select * from produit where nom = '"+ nom +"' and prix = '"+prix+"' ;" ;
		Produit unProduit = null ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unProduit = new Produit (
						unRes.getInt("id_produit"), unRes.getString("nom"),  unRes.getFloat("prix"),unRes.getString("tpsPreparation"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		return unProduit ; 
	}

	
	public static ArrayList<Produit> selectAllProduits (String mot)
	{
		String requete ; 
		if (mot.equals("")) {
			requete ="select * from produit ;" ;
		}else {
			requete ="select * from produit where nom like '%"+mot+"%' ; " ;
		}
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();  
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Produit unProduit = new Produit (desRes.getInt("id_produit"), desRes.getString("nom"), desRes.getFloat("prix"), desRes.getString("tpsPreparation"));
				lesProduits.add(unProduit);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		return lesProduits ; 
	}

	
	// ############################################################ CUISINIER ############################################################
	
	//1
	public static Cuisinier selectWhereCuisinier(String nom, String prenom) {
		// TODO Auto-generated method stub
		String requete ="select * from cuisinier where nom = '"+ nom +"' and prenom = '"+prenom +"' ;" ;
		Cuisinier unCuisinier = null ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) 
			{
				unCuisinier = new Cuisinier (unRes.getInt("id_cuisinier"), unRes.getString("nom"), unRes.getString("prenom"), unRes.getString("adresse"), 
						unRes.getString("tel"), unRes.getString("mail"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		return unCuisinier ; 
	}
	
	//2
	public static Cuisinier selectWhereCuisinier (int id_cuisinier)
	{
		String requete ="select * from cuisinier where id_cuisinier = "+ id_cuisinier +";" ;
		Cuisinier unCuisinier = null ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unCuisinier = new Cuisinier (unRes.getInt("id_cuisinier"), unRes.getString("nom"), unRes.getString("prenom"), unRes.getString("adresse"), 
						unRes.getString("tel"), unRes.getString("mail"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		return unCuisinier ; 
	}


	public static void insertCuisinier(Cuisinier unCuisinier) {
		// TODO Auto-generated method stub
		String requete ="insert into cuisinier values (null, '" + unCuisinier.getNom() + "','" + unCuisinier.getPrenom()
		+"','" + unCuisinier.getAdresse() + "','" + unCuisinier.getTel() + "','" +unCuisinier.getMail() + ");"; 
		executerRequete(requete);
	}


	public static ArrayList<Cuisinier> selectAllCuisiniers(String mot) {
		// TODO Auto-generated method stub
		String requete ; 
		if (mot.equals("")) 
		{
			requete ="select * from cuisinier ;" ;
		}
		else 
		{
			requete ="select * from cuisinier where nom like '%"+mot+"%' or prenom like '%"+mot+"%' ; " ;
		}
		ArrayList<Cuisinier> lesCuisiniers = new ArrayList<Cuisinier>();  
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) 
			{
				Cuisinier unCuisinier = new Cuisinier
						(desRes.getInt("id_cuisinier"),
						desRes.getString("nom"), desRes.getString("prenom"),
						desRes.getString("adresse"), desRes.getString("tel"),
								desRes.getString("mail") );
				lesCuisiniers.add(unCuisinier);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete );
		}
		return lesCuisiniers ; 
	}


	public static void deleteCuisinier(int id_cuisinier) {
		// TODO Auto-generated method stub
		String requete = "delete from cuisinier where id_cuisinier = " +id_cuisinier+";";
		executerRequete(requete);
	}

	public static void updateCuisinier(Cuisinier unCuisinier) {
		// TODO Auto-generated method stub
		String requete ="update cuisinier set " +
				"nom = '" + unCuisinier.getNom() + "', " +
				"prenom = '" + unCuisinier.getPrenom() +"', " +
				"adresse = '" + unCuisinier.getAdresse() + ", " +
				"tel = "+ unCuisinier.getTel()+ ", " +
				"mail = " +unCuisinier.getMail() +" ;" ;
		executerRequete(requete);
		
	}

	/********************************* gestion des Clients
	 * @param unClient****************************************************/

	public static void insertClient (Client unClient)
	{
		String requete ="insert into client values (null, '" + unClient.getNom() + "','" + unClient.getPrenom()
				+"','" + unClient.getAdresse() + "','" + unClient.getTel() + "','" + unClient.getMail() +  "' );" ;
		executerRequete(requete);
	}

	public static void deleteClient  (int id_client)
	{
		String requete =" delete from client where id_client = " + id_client +" ; " ;
		executerRequete(requete);
	}

	public static void updateClient  (Client unClient)
	{
		String requete ="update client set nom = '" + unClient.getNom()
				+ "', prenom = '" + unClient.getPrenom()
				+ "', adresse = '" + unClient.getAdresse()+"', tel= " + unClient.getTel() + "', mail = '" + unClient.getMail()
				+ "  where id_client = " + unClient.getId_client() + " ;" ;
		executerRequete(requete);
	}

	public static Client selectWhereClient (int id_client)
	{
		String requete ="select * from client where id_client = "+ id_client +";" ;
		Client unClient = null ;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unClient = new Client (
						unRes.getInt("id_client"), unRes.getString("nom"), unRes.getString("prenom"),
						unRes.getString("adresse"), unRes.getString("tel"), unRes.getString("mail")
				);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return unClient  ;
	}

	//surcharge de la méthode avec de nouveaux arguments
	public static Client selectWhereClient (String nom, String prenom)
	{
		String requete ="select * from client where nom = '"+ nom +"' and prenom = '"+prenom +"' ;" ;
		Client unClient = null ;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unClient = new Client (
						unRes.getInt("id_client"), unRes.getString("nom"), unRes.getString("prenom"),
						unRes.getString("adresse"), unRes.getString("tel"), unRes.getString("mail")
				);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return unClient ;
	}

	public static ArrayList<Client> selectAllClient (String mot){

		String requete ;
		if (mot.equals("")) {
			requete ="select * from client ;" ;
		}else {
			requete ="select * from client where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or adresse like '%" + mot + "%' ; " ;
		}
		ArrayList<Client> lesClients = new ArrayList<Client>();

		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Client unClient = new Client (
						desRes.getInt("id_client"), desRes.getString("nom"), desRes.getString("prenom"),
						desRes.getString("adresse"), desRes.getString("tel"), desRes.getString("mail")
				);
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de la requete : " + requete );
		}
		return lesClients ;
	}


}