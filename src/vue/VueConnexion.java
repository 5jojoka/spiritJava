package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;
import controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JButton btSeConnecter = new JButton("Se Connecter"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JTextField txtEmail = new JTextField("a@gmail.com"); 
	private JPasswordField txtMdp = new JPasswordField("123"); 
	
	private JPanel panelConnexion = new JPanel(); 
	private JPanel panelMenu = new JPanel (); 
	
	private JButton btClient = new JButton("Gestion Clients");
	private JButton btProduit = new JButton("Gestion Produits"); 
	private JButton btCuisinier = new JButton("Gestion Cuisinier"); 
//	private JButton btCommander = new JButton("Gestion des Commandes"); 
	private JButton btPreparer = new JButton("Affectation Preparations");
	private JButton btQuitter = new JButton("Quitter l'application"); 
	
	
	public VueConnexion() {
		this.setBounds(100, 200, 700, 300);
		this.setTitle("Connexion à l'application Spirit Gourmet");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.getContentPane().setBackground(new Color (158, 148, 148 ));
		
		this.panelConnexion.setLayout(new GridLayout(3, 2));
		this.panelConnexion.setBounds(340, 60, 300, 140);
		this.panelConnexion.setBackground(new Color (158, 148, 148 ));
		this.panelConnexion.add(new JLabel("Email : ")); 
		this.panelConnexion.add(this.txtEmail); 
		this.panelConnexion.add(new JLabel("MDP : ")); 
		this.panelConnexion.add(this.txtMdp); 
		this.panelConnexion.add(this.btAnnuler); 
		this.panelConnexion.add(this.btSeConnecter); 
		
		ImageIcon uneImage = new ImageIcon("src/images/logo.PNG");
		JLabel monLogo = new JLabel(uneImage); 
		monLogo.setBounds(20, 60, 300, 150);
		this.add(monLogo);
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les TXT ecoutable sur frappe de touche 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.add(this.panelConnexion);
		
		//construction du panel Menu 
		this.panelMenu.setLayout(new GridLayout(2, 2));
		this.panelMenu.setBounds(340, 60, 300, 140);
		this.panelMenu.setBackground(new Color (158, 148, 148 ));

		this.panelMenu.add(this.btClient); 
		this.panelMenu.add(this.btProduit); 
		this.panelMenu.add(this.btCuisinier); 
		this.panelMenu.add(this.btQuitter); 
		
		this.panelMenu.setVisible(false);
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables du menu 
		this.btClient.addActionListener(this);
		this.btProduit.addActionListener(this);
		this.btCuisinier.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}else if (e.getSource() == this.btSeConnecter) {
			this.traitement();
		}
		else if (e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-Vous Quitter l'Application !", "Quitter L'application", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
				this.panelConnexion.setVisible(true);
				this.panelMenu.setVisible(false);
			}
		}else if (e.getSource() == this.btProduit) {
			
			//on rend invisible la vue connexion 
			this.setVisible(false);
			//on instancie la vue produit 
			Main.instancierVueProduit();
			
		}else if (e.getSource() == this.btCuisinier) {
			//on rend invisible la vue connexion
			this.setVisible(false);
			//on instancie la vue produit
			Main.instancierVueCuisinier();

		}else if (e.getSource() == this.btClient) {
			//on rend invisible la vue connexion
			this.setVisible(false);
			//on instancie la vue produit
			Main.instancierVueClient();
		}
	}
	
	public void traitement () {
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		
		User unUser = Main.verifConnexion(email, mdp); 
		if (unUser == null) {
			JOptionPane.showMessageDialog(this, "Erreur de connexion, vérifiez vos identifiants");
		}else {
			JOptionPane.showMessageDialog(this, "Bienvenue " + unUser.getNom()+"  "+unUser.getPrenom());
			// Ouverture du menu général 
			this.panelConnexion.setVisible(false);
			this.panelMenu.setVisible(true);
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) // touche entrée 
		{
			this.traitement();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
