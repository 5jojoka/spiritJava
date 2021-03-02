package vue;

import java.awt.*;
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

import controleur.Cuisinier;
import controleur.Main;
import controleur.Tableau;

public class VueCuisinier extends JFrame implements ActionListener {

	private JButton btRetour = new JButton("Retour au menu");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btFiltrer = new JButton("Filtrer");

	private JPanel panelAjout = new JPanel();
	private JPanel panelLister = new JPanel();

	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtEmail = new JTextField();

	private JTextField txtMot = new JTextField(); //pour la recherche

	private Tableau unTableau;
	private JTable uneTable;
	private JScrollPane uneScroll;

	public VueCuisinier() {
		this.setBounds(100, 100, 900, 500);
		this.setTitle("Gestion des cuisiniers de Spirit Gourmet");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);

		this.getContentPane().setBackground(new Color(158, 148, 148));

		//construction du panel ajout 
		this.panelAjout.setBounds(40, 100, 300, 250);
		this.panelAjout.setBackground(Color.white);
		this.panelAjout.setLayout(new GridLayout(6, 2));
		this.panelAjout.add(new JLabel("Nom : "));
		this.panelAjout.add(this.txtNom);
		this.panelAjout.add(new JLabel("Prenom : "));
		this.panelAjout.add(this.txtPrenom);
		this.panelAjout.add(new JLabel("Adresse : "));
		this.panelAjout.add(this.txtAdresse);
		this.panelAjout.add(new JLabel("Telephone : "));
		this.panelAjout.add(this.txtTel);
		this.panelAjout.add(new JLabel("Email : "));
		this.panelAjout.add(this.txtEmail);

		this.panelAjout.add(this.btAnnuler);
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);

		//installer le bouton retour 
		this.btRetour.setBounds(700, 400, 140, 30);
		this.add(this.btRetour);
		this.btRetour.addActionListener(this);// this -> pour agir dans la fenêtre

		//construction du panelLister 
		this.panelLister.setBackground(Color.white);
		this.panelLister.setLayout(null);
		this.add(this.panelLister);
		this.remplirPanelLister("");

		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);

		//suppression d'un pilote de la table

		this.uneTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					int ligne = uneTable.getSelectedRow();
					System.out.println(ligne);
					int id_cuisinier = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce cuisinier ?", "Suppression", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						//suppression dans la base
						Main.deleteCuisinier(id_cuisinier);
						//suppression dans la table d'affichage
						unTableau.deleteLigne(ligne);
						JOptionPane.showMessageDialog(null, "Suppression reussie");
					}
				} else if (e.getClickCount() == 1) {
					int ligne = uneTable.getSelectedRow();
					txtNom.setText(unTableau.getValueAt(ligne, 1).toString());
					txtPrenom.setText(unTableau.getValueAt(ligne, 2).toString());
					txtAdresse.setText(unTableau.getValueAt(ligne, 3).toString());
					txtTel.setText(unTableau.getValueAt(ligne, 4).toString());
					txtEmail.setText(unTableau.getValueAt(ligne, 5).toString());
					btEnregistrer.setText("Modifier");
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		//filter les pilotes par un mot de recherche
		this.txtMot.setBounds(450, 40, 130, 20);
		this.add(this.txtMot);
		this.btFiltrer.setBounds(590, 40, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btRetour) {
			this.dispose();    //retour au menu
			Main.rendreVisible(true);
		} else if (e.getSource() == this.btAnnuler) {
			this.viderLesChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			this.insertCuisinier();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			this.updateCuisinier();
		} else if (e.getSource() == this.btFiltrer) {
			this.remplirPanelLister(this.txtMot.getText());
		}
	}

	private void viderLesChamps() {
		// TODO Auto-generated method stub
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		this.txtTel.setText("");
		this.txtEmail.setText("");
	}

	public void updateCuisinier() {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String adresse = this.txtAdresse.getText();
		String tel = this.txtTel.getText();
		String email = this.txtEmail.getText();
		int numLigne = uneTable.getSelectedRow();
		int idCuisinier = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
		Cuisinier unCuisinier = new Cuisinier(idCuisinier, nom, prenom, adresse, tel, email);
		//update dans la base de données
		Main.updateCuisinier(unCuisinier);

		//modifiaction dans l'affichage tableau
		Object[] ligne = {unCuisinier.getId_cuisinier(), nom, prenom, adresse, tel, email + ""};
		this.unTableau.updateLigne(numLigne, ligne);

		JOptionPane.showMessageDialog(this, "Modification reussie !");
		this.viderLesChamps();
	}

	public void insertCuisinier() {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String adresse = this.txtAdresse.getText();
		String tel = this.txtTel.getText();
		String email = this.txtEmail.getText();
		Cuisinier unCuisinier = new Cuisinier(nom, prenom, adresse, tel, email);
		//insertion dans la base de données
		Main.insertCuisinier(unCuisinier);

		//recuperation de l'id a travers un select where
		unCuisinier = Main.selectWhereCuisinier(nom, prenom);

		//insertion dans l'affichage tableau
		Object ligne[] = {unCuisinier.getId_cuisinier(), nom, prenom, adresse, tel, email + ""};
		this.unTableau.insertLigne(ligne);

		JOptionPane.showMessageDialog(this, "Insertion reussie !");
		this.viderLesChamps();
	}

	public Object[][] getDonnees(String mot) {
		//recuperer les cuisiniers de la bdd
		ArrayList<Cuisinier> lesCuisiniers = Main.selectAllCuisiniers(mot);

		//transformation des cuisiniers en matrice de données
		Object donnees[][] = new Object[lesCuisiniers.size()][6];
		int i = 0;
		for (Cuisinier unCuisinier : lesCuisiniers) {
			donnees[i][0] = unCuisinier.getId_cuisinier() + "";
			donnees[i][1] = unCuisinier.getNom();
			donnees[i][2] = unCuisinier.getPrenom();
			donnees[i][3] = unCuisinier.getAdresse();
			donnees[i][4] = unCuisinier.getTel();
			donnees[i][5] = unCuisinier.getMail();
			i++;
		}
		return donnees;
	}

	public void remplirPanelLister(String mot) {
		this.panelLister.removeAll();
		String entetes[] = {"ID", "Nom", "Prenom", "Adresse", "Telephone", "Email"};
		Object donnees[][] = this.getDonnees(mot);
		this.unTableau = new Tableau(donnees, entetes);
		this.uneTable = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(this.uneTable);
		this.panelLister.setBounds(340, 80, 530, 300);

		this.uneScroll.setBounds(20, 20, 510, 280);
		this.panelLister.add(this.uneScroll);
	}
}

