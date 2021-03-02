package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Main;
import controleur.Produit;
import controleur.Tableau;

public class VueProduit extends JFrame implements ActionListener 
{
	private JButton btRetour = new JButton("Retour au Menu"); 
	private JPanel panelAjout = new JPanel(); 
	private JPanel panelLister = new JPanel(); 
	
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrix = new JTextField(); 
	private JTextField txtTpsPreparation= new JTextField(); 
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ;
	//knzdchoisdnbhcoschnosdc
	private Tableau unTableau ;
	
	private JTextField txtMot = new JTextField ();
	private JButton btFiltrer = new JButton("filtrer"); 
	
	public VueProduit ()
	{
		this.setBounds(100, 100, 900, 500);
		this.setTitle("Gestion des produits de Spirit Gourmet");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (158, 148, 148 ));
		
		//installer le bouton retour 
		this.btRetour.setBounds(750, 440, 140, 30);
		this.add(this.btRetour); 
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(new Color (158, 148, 148 ));
		this.panelAjout.setLayout(new GridLayout(4,2));
		this.panelAjout.add(new JLabel("Nom du produit :")); 
		this.panelAjout.add(this.txtNom);
		this.panelAjout.add(new JLabel("Prix du produit :"));
		this.panelAjout.add(this.txtPrix);
		this.panelAjout.add(new JLabel("Temps de préparation :")); 
		this.panelAjout.add(this.txtTpsPreparation);
		this.panelAjout.add(this.btAnnuler); 
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		
		//construire le panel Lister 
		this.panelLister.setBackground(new Color (158, 148, 148 ));
		this.panelLister.setLayout(null);
		this.add(this.panelLister); 
		
		this.remplirPanelLister ("");  
		
		
		//suppression d'un Produit de la table 
		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >=2) {
					int ligne = uneTable.getSelectedRow();
					System.out.println(ligne);
					int id_produit = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString()); 
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce Produit ?", "Suppression", JOptionPane.YES_NO_OPTION); 
					if (retour == 0) {
						//suppression dans la base 
						Main.deleteProduit(id_produit);
						//suppression dans la table d'affichage 
						unTableau.deleteLigne(ligne);
						JOptionPane.showMessageDialog(null, "Suppression réussie");
					}
				}else if (e.getClickCount() ==1) {
					int ligne = uneTable.getSelectedRow();
					txtNom.setText(unTableau.getValueAt(ligne, 1).toString());
					txtPrix.setText(unTableau.getValueAt(ligne, 2).toString());
					txtTpsPreparation.setText(unTableau.getValueAt(ligne, 3).toString());
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
		//filter les Produits par un mot de recherche 
		this.txtMot.setBounds(450, 40, 100, 20);
		this.add(this.txtMot); 
		this.btFiltrer.setBounds(580, 40, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		
		this.setVisible(true);
	}

	public void remplirPanelLister(String mot) {
		
		this.panelLister.removeAll();
		String entetes [] = {"Id_produit", "Nom", "Prix", "Temps Prepatation"};
		Object donnees [][] = this.getDonnees (mot) ;		
		this.unTableau = new Tableau (donnees, entetes); 
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.panelLister.setBounds(350, 80, 530, 300);
		
		this.uneScroll.setBounds(20, 20, 510, 280);
		this.panelLister.add(this.uneScroll);
		
	}

	public Object [] [] getDonnees(String mot) {
		//recuperer les produits de la bdd 
		ArrayList<Produit> lesProduits = Main.selectAllProduits(mot); 
		//transofrmation des Produits en matrice de données 
		Object donnees [][] = new Object [lesProduits.size()][4];
		int i = 0 ; 
		for (Produit unProduit : lesProduits) {
			donnees[i][0] = unProduit.getId_produit()+""; 
			donnees[i][1] = unProduit.getNom(); 
			donnees[i][2] = unProduit.getPrix(); 
			donnees[i][3] = unProduit.getTpsPreparation()+ ""; 
			i++; 
		}
				
		return donnees;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btRetour) {
			this.dispose ();
			//retour au menu
			Main.rendreVisible(true);
		}
		else if (e.getSource() == this.btAnnuler)
		{
			this.viderLesChamps (); 
		}
		else if (e.getSource()  == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) 
		{
			this.insertProduit (); 
		}
		else if (e.getSource()  == this.btEnregistrer && e.getActionCommand().equals("Modifier")) 
		{
			this.updateProduit (); 
		}else if (e.getSource() == this.btFiltrer)
		{
			this.remplirPanelLister(this.txtMot.getText());
		}
	}

	public void updateProduit() {
		String nom = this.txtNom.getText(); 
		String tpsPreparation = this.txtTpsPreparation.getText(); 
 
		int prix = 0; 
		try {
			prix = Integer.parseInt(this.txtPrix.getText());
		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"Attention au format du prix produit !");
			prix = -1 ;
		}
		if (prix >=0 ) {
			int numLigne = uneTable.getSelectedRow(); 
			int id_produit = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString ());
			Produit unProduit = new Produit(id_produit,nom, tpsPreparation, prix);
			//update dans la base de données 
			Main.updateProduit(unProduit);
			
			//modifiaction dans l'affichage tableau 
			Object ligne[] = {unProduit.getId_produit(), nom, prix, tpsPreparation+""};
			this.unTableau.updateLigne(numLigne, ligne);
			
			JOptionPane.showMessageDialog(this,"Modification réussie !");
			this.viderLesChamps();
		} else {
			this.txtPrix.setBackground(Color.red);
		}
		
	}

	public void insertProduit() {
		String nom = this.txtNom.getText(); 
		String tpsPreparation = this.txtTpsPreparation.getText();
		
		int prix = 0; 
		try {
			prix = Integer.parseInt(this.txtPrix.getText());
		}
		catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"Attention au format du nombre d'heures de vols !");
			prix = -1 ;
		}
		if (prix >=0 ) {
			Produit unProduit = new Produit(nom, prix, tpsPreparation);
			//insertion dans la base de données 
			Main.insertProduit(unProduit);
			
			//recuperation de l'id a travers un select where 
			unProduit = Main.selectWhereProduit(nom, tpsPreparation);
			
			//insertion dans l'affichage tableau 
			Object ligne[] = {unProduit.getId_produit(), nom, prix, tpsPreparation+""};
			this.unTableau.insertLigne(ligne);
			
			JOptionPane.showMessageDialog(this,"Insertion réussie !");
			this.viderLesChamps();
		} else {
			this.txtPrix.setBackground(Color.red);
		}
		
	}

	public void viderLesChamps() {
		//vider l'ensemble des champs du formulaire 
		 this.txtNom.setText("");
		 this.txtTpsPreparation.setText("");
		 this.txtPrix.setBackground(Color.white);
		 this.btEnregistrer.setText("Enregistrer");
	}
	

}
