package controleur;

import java.util.ArrayList;

import modele.Modele;
import vue.VueClient;
import vue.VueConnexion;
import vue.VueCuisinier;
import vue.VueProduit;

public class Main {

	private static VueConnexion uneVueConnexion ;
	private static VueProduit uneVueProduit ;
	private static VueCuisinier uneVueCuisinier ;
	private static VueClient uneVueClient;

	public static void main (String [] args) {
		uneVueConnexion = new VueConnexion(); 
	}
	
	/***************************************************** Gestion des vues ******************************************/
	public static void instancierVueClient () {uneVueClient = new VueClient();}

	public static void instancierVueProduit () 
	{
		uneVueProduit =new VueProduit(); 
	}

	public static void instancierVueCuisinier ()
	{
		uneVueCuisinier = new VueCuisinier();
	}
	
	public static void rendreVisible (boolean action) 
	{
		uneVueConnexion.setVisible(action);
	}
	
	public static User verifConnexion (String email, String mdp) {
		//controle des donnees 
		return Modele.verifConnexion(email, mdp);
	}
	
	/*************************************************** Controleur Produit ********************************************/
	
	public static void insertProduit (Produit unProduit)
	{
		//ici on réalise des controles avant insertion 
		Modele.insertProduit(unProduit);
	}
	
	public static void deleteProduit (int id_produit)
	{
		Modele.deleteProduit(id_produit);
	}
	
	public static void updateProduit (Produit unProduit)
	{
		//ici on réalise des controles avant mise à jour  
		Modele.updateProduit(unProduit);
	}
	
	public static Produit selectWhereProduit (int id_produit)
	{
		return Modele.selectWhereProduit(id_produit);
	}
	
	public static Produit selectWhereProduit (String nom, String tpsPreparation)
	{
		return Modele.selectWhereProduit(nom, tpsPreparation);
	}
	
	
	public static ArrayList<Produit> selectAllProduits  (String mot)
	{
		return Modele.selectAllProduits(mot);
	}
	
	
	/*************************************************** Controleur Cuisinier ********************************************/

	
	public static ArrayList<Cuisinier> selectAllCuisiniers(String mot) {
		// TODO Auto-generated method stub
		return Modele.selectAllCuisiniers(mot);
	}

	public static void insertCuisinier(Cuisinier unCuisinier) {
		// TODO Auto-generated method stub
		Modele.insertCuisinier(unCuisinier);
	}
	
	public static void deleteCuisinier (int id_cuisinier)
	{
		Modele.deleteCuisinier(id_cuisinier);
	}
	
	public static void updateCuisinier (Cuisinier unCuisinier)
	{
		//ici on réalise des controles avant mise à jour  
		Modele.updateCuisinier(unCuisinier);
	}

	public static Cuisinier selectWhereCuisinier(String nom, String prenom) {
		return Modele.selectWhereCuisinier(nom,prenom);
	}

	/*************************************************** Controleur Client ********************************************/


	public static ArrayList<Client> selectAllClient(String mot) {
		// TODO Auto-generated method stub
		return Modele.selectAllClient(mot);
	}

	public static Client selectWhereClient(String nom, String prenom) {
		// TODO Auto-generated method stub
		return Modele.selectWhereClient(nom,prenom);
	}

	public static void insertClient(Client unClient) {
		// TODO Auto-generated method stub
		Modele.insertClient(unClient);
	}

	public static void deleteClient (int id_client)
	{
		Modele.deleteClient(id_client);
	}

	public static void updateClient (Client unClient)
	{
		//ici on réalise des controles avant mise à jour
		Modele.updateClient(unClient);
	}
}
