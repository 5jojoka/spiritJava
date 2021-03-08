package vue;



import controleur.Client;
import controleur.Main;
import controleur.Tableau;

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


public class VueClient extends JFrame implements ActionListener {
    private JButton btRetour = new JButton("Retour au Menu");
    private JPanel panelAjout = new JPanel();
    private JPanel panelLister = new JPanel();

    private JButton btEnregistrer = new JButton("Enregistrer");
    private JButton btAnnuler = new JButton("Annuler");

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtTel = new JTextField();
    private JTextField txtNbCommandePassees = new JTextField();

    private JTable uneTable;
    private JScrollPane uneScroll;
    private Tableau unTableau;

    private JTextField txtMot = new JTextField();
    private JButton btFiltrer = new JButton("filtrer");

    public VueClient() {
        this.setBounds(100, 100, 900, 500);
        this.setTitle("Gestion des clients de Spirit Gourmet");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.getContentPane().setBackground(new Color (158, 148, 148 ));

        //installer le bouton retour
        this.btRetour.setBounds(700, 400, 140, 30);
        this.add(this.btRetour);
        this.btRetour.addActionListener(this);

        //construction du panel Ajout
        this.panelAjout.setBounds(40, 100, 300, 250);
        this.panelAjout.setBackground(new Color (158, 148, 148 ));
        this.panelAjout.setLayout(new GridLayout(7, 2));
        this.panelAjout.add(new JLabel("Nom :"));
        this.panelAjout.add(this.txtNom);
        this.panelAjout.add(new JLabel("Prenom :"));
        this.panelAjout.add(this.txtPrenom);
        this.panelAjout.add(new JLabel("Adresse :"));
        this.panelAjout.add(this.txtAdresse);
        this.panelAjout.add(new JLabel("Telephone :"));
        this.panelAjout.add(this.txtTel);
        this.panelAjout.add(new JLabel("Nb commandes passees :"));
        this.panelAjout.add(this.txtNbCommandePassees);
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

        this.remplirPanelLister("");


        //suppression d'un pilote de la table

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
                if (e.getClickCount() >= 2) {
                    int ligne = uneTable.getSelectedRow();
                    System.out.println(ligne);
                    int id_client = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
                    int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce pilote ?", "Suppression", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression dans la base
                        Main.deleteClient(id_client);
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
                    txtNbCommandePassees.setText(unTableau.getValueAt(ligne, 5).toString());
                    btEnregistrer.setText("Modifier");
                }

            }
        });

        //filter les pilotes par un mot de recherche
        this.txtMot.setBounds(450, 40, 100, 20);
        this.add(this.txtMot);
        this.btFiltrer.setBounds(580, 40, 100, 20);
        this.add(this.btFiltrer);
        this.btFiltrer.addActionListener(this);

        this.setVisible(true);
    }

    public void remplirPanelLister(String mot) {

        this.panelLister.removeAll();
        String entetes[] = {"Id", "Nom", "Prenom", "Adresse", "Telephone", "Nb commande passee"};
        Object donnees[][] = this.getDonnees(mot);
        this.unTableau = new Tableau(donnees, entetes);
        this.uneTable = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.uneTable);
        this.panelLister.setBounds(350, 80, 530, 300);

        this.uneScroll.setBounds(20, 20, 510, 280);
        this.panelLister.add(this.uneScroll);

    }

    public Object[][] getDonnees(String mot) {
        //recuperer les pilotes de la bdd
        ArrayList<Client> lesClients = Main.selectAllClient(mot);
        //transofrmation des pilotes en matrice de données
        Object donnees[][] = new Object[lesClients.size()][6];
        int i = 0;
        for (Client unClient : lesClients) {
            donnees[i][0] = unClient.getId_client() + "";
            donnees[i][1] = unClient.getNom();
            donnees[i][2] = unClient.getPrenom();
            donnees[i][3] = unClient.getAdresse();
            donnees[i][4] = unClient.getTel();
            donnees[i][5] = unClient.getNbCommandePassees();
            i++;
        }

        return donnees;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btRetour) {
            this.dispose();
            //retour au menu
            Main.rendreVisible(true);
        } else if (e.getSource() == this.btAnnuler) {
            this.viderLesChamps();
        } else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
            this.insertClient();
        } else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
            this.updateClient();
        } else if (e.getSource() == this.btFiltrer) {
            this.remplirPanelLister(this.txtMot.getText());
        }
    }

    public void updateClient() {
        String nom = this.txtNom.getText();
        String prenom = this.txtPrenom.getText();
        String adresse = this.txtAdresse.getText();
        String tel = this.txtTel.getText();
        int nbCommandePassees = 0;
        try {
            nbCommandePassees = Integer.parseInt(this.txtNbCommandePassees.getText());
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(this, "Attention au format du nombre de commandes passees !");
            nbCommandePassees = -1;
        }
        if (nbCommandePassees >= 0) {
            int numLigne = uneTable.getSelectedRow();
            int id_client = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
            Client unClient = new Client(id_client, nom, prenom, adresse, tel, nbCommandePassees);
            //update dans la base de données
            Main.updateClient(unClient);

            //modifiaction dans l'affichage tableau
            Object ligne[] = {unClient.getId_client(), nom, prenom, adresse, tel, nbCommandePassees + ""};
            this.unTableau.updateLigne(numLigne, ligne);

            JOptionPane.showMessageDialog(this, "Modification reussie !");
            this.viderLesChamps();
        } else {
            this.txtNbCommandePassees.setBackground(Color.red);
        }

    }

    public void insertClient() {
        String nom = this.txtNom.getText();
        String prenom = this.txtPrenom.getText();
        String adresse = this.txtAdresse.getText();
        String tel = this.txtTel.getText();
        int nbCommandePassees = 0;
        try {
            nbCommandePassees = Integer.parseInt(this.txtNbCommandePassees.getText());
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(this, "Attention au format du nombre de commandes passees !");
            nbCommandePassees = -1;
        }
        if (nbCommandePassees >= 0) {
            Client unClient = new Client(nom, prenom, adresse, tel, nbCommandePassees);
            //insertion dans la base de données
            Main.insertClient(unClient);

            //recuperation de l'id a travers un select where
            unClient = Main.selectWhereClient(nom, prenom);

            //insertion dans l'affichage tableau
            Object ligne[] = {unClient.getId_client(), nom, prenom, adresse, tel, nbCommandePassees + ""};
            this.unTableau.insertLigne(ligne);

            JOptionPane.showMessageDialog(this, "Insertion reussie !");
            this.viderLesChamps();
        } else {
            this.txtNbCommandePassees.setBackground(Color.red);
        }

    }

    public void viderLesChamps() {
        //vider l'ensemble des champs du formulaire
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtAdresse.setText("");
        this.txtTel.setText("");
        this.txtNbCommandePassees.setBackground(Color.white);
        this.btEnregistrer.setText("Enregistrer");
    }
}
