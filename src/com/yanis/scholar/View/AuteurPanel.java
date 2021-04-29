package com.yanis.scholar.View;

import com.yanis.scholar.Control.Auteur;
import com.yanis.scholar.Model.AuteurEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Yanis OULHACI
 */
public class AuteurPanel extends JPanel {
    private final JPanel panelPrincipal = new JPanel();
    
        // Section Hauteur
    private JTextField txtNomAuteur = new JTextField();
    private JTextField txtPrenomAuteur = new JTextField();
    private JTextField txtEmailAuteur = new JTextField();
        
        // Section Université
    private JTextField txtNomUniversite = new JTextField();
    private JTextField txtSiteUniversite = new JTextField();
    private JTextField txtAdressUniversite = new JTextField();
    private JPanel pUniversiteInfo = new JPanel();
    
    // Les buttons
    private JButton btnAuteurExiste = new JButton("Selectionner un auteur existant");
    private JButton btnUniversiteExiste = new JButton("Selectionner une université existante");
    
    
    public AuteurPanel() {
        // Les bordure
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        EmptyBorder emptyBorder = new EmptyBorder(2, 15, 15, 15);
        EmptyBorder emptyBorderSecondary = new EmptyBorder(3, 0, 3, 0);
        
        JPanel sectionAauteur = new JPanel(); sectionAauteur.setBorder(blackline);
        JPanel pBody = new JPanel();
        
        // Les labels
        JLabel labelTitle = new JLabel("Ajouter un auteur");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        
            // Section Auteur
        JLabel labelInformationAuteur = new JLabel("Information sur l'auteur"); labelInformationAuteur.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel labelNomAuteur = new JLabel("Nom de l'auteur");
        JLabel labelPrenomAuteur = new JLabel("Prénom de l'auteur");
        JLabel labelEmailAuteur = new JLabel("Email de l'auteur");
        JLabel labelUniversite = new JLabel("L'université de l'auteur");
        
            // Section Universite
        JLabel labelInformationUniversite = new JLabel("Information sur l'université"); labelInformationUniversite.setFont(new Font("SansSerif", Font.BOLD, 15)); labelInformationUniversite.setBorder(new EmptyBorder(30, 0, 0, 0));
        JLabel labelNomUniversite = new JLabel("Nom de l'universite");
        JLabel labelSiteUniversite = new JLabel("Site de l'universite");
        JLabel labelAdressUniversite = new JLabel("Adress de l'universite");
        
        // Les inputs
        
            // Section Auteur
        txtNomAuteur = new JTextField(); txtNomAuteur.setColumns(12); txtNomAuteur.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 16));
        txtPrenomAuteur = new JTextField(); txtPrenomAuteur.setColumns(10); txtPrenomAuteur.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 16));
        txtEmailAuteur = new JTextField(); txtEmailAuteur.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 16));
        
            // Section Université
        txtNomUniversite = new JTextField(); txtNomUniversite.setColumns(20); txtNomUniversite.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 16));
        txtSiteUniversite = new JTextField(); txtSiteUniversite.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 16));
        txtAdressUniversite = new JTextField(); txtAdressUniversite.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 16));
        
        
        
        // panel grouping element
        JPanel pTitreAjouterAuteur = new JPanel();
        pTitreAjouterAuteur.add(labelTitle);
            
            // Titre de la section auteur
        JPanel pTitreSectionAuteur = new JPanel();
        pTitreSectionAuteur.setLayout(new BorderLayout());
        pTitreSectionAuteur.add(labelInformationAuteur,BorderLayout.WEST);
        pTitreSectionAuteur.setBorder(new EmptyBorder(0, 0, 10, 0));
        
            // Titre de la section universite
        JPanel pTitreSectionUniversite = new JPanel();
        pTitreSectionUniversite.setLayout(new BorderLayout());
        pTitreSectionUniversite.add(labelInformationUniversite,BorderLayout.WEST);
        pTitreSectionUniversite.setBorder(new EmptyBorder(0, 0, 10, 0));
        
            // nom, prénom et email auteur:
        JPanel pAuteurInfo = new JPanel();
        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createVerticalBox();
        Box b3 = Box.createVerticalBox();
        b1.add(labelNomAuteur);
        b1.add(txtNomAuteur);
        b2.add(labelPrenomAuteur);
        b2.add(txtPrenomAuteur);
        b3.add(labelEmailAuteur);
        b3.add(txtEmailAuteur);
            b3.setBorder(emptyBorderSecondary);
        pAuteurInfo.setLayout(new BorderLayout());
        pAuteurInfo.add(b1, BorderLayout.WEST);
        pAuteurInfo.add(b2, BorderLayout.EAST);
        pAuteurInfo.add(b3, BorderLayout.SOUTH);
        
            // nom, site, adress univ:
        Box b4 = Box.createVerticalBox();
        Box b5 = Box.createVerticalBox();
        Box b6 = Box.createVerticalBox();
        b4.add(labelNomUniversite);
        b4.add(txtNomUniversite);
        b5.add(labelSiteUniversite);
        b5.add(txtSiteUniversite);
        b6.add(labelAdressUniversite);
        b6.add(txtAdressUniversite);
            b5.setBorder(emptyBorderSecondary);
            b6.setBorder(emptyBorderSecondary);
        pUniversiteInfo.setLayout(new BorderLayout());
        pUniversiteInfo.add(b4, BorderLayout.NORTH);
        pUniversiteInfo.add(b5, BorderLayout.CENTER);
        pUniversiteInfo.add(b6, BorderLayout.SOUTH);
            
        
            // button check auteur / université
        JPanel pBtnSelectAuteur = new JPanel();
        pBtnSelectAuteur.add(btnAuteurExiste);
            pBtnSelectAuteur.setBorder(emptyBorderSecondary);
        JPanel pBtnSelectUniversite = new JPanel(); pBtnSelectUniversite.add(btnUniversiteExiste);
            pBtnSelectUniversite.setBorder(emptyBorderSecondary);
        
            
        // Insertionsur la section auteur / université
        Box boxSectionAuteur = Box.createVerticalBox();
        boxSectionAuteur.setBorder(emptyBorder);
            
            // Auteur
        boxSectionAuteur.add(pTitreSectionAuteur);
        boxSectionAuteur.add(pAuteurInfo);
        boxSectionAuteur.add(pBtnSelectAuteur);
        
            // Université
        boxSectionAuteur.add(pTitreSectionUniversite);
        boxSectionAuteur.add(pUniversiteInfo);
        boxSectionAuteur.add(pBtnSelectUniversite);
        
        sectionAauteur.add(boxSectionAuteur);
        pBody.add(sectionAauteur);
        
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(pTitreAjouterAuteur, BorderLayout.NORTH);
        panelPrincipal.add(pBody, BorderLayout.CENTER);
    }
    
    // Les méthodes
    public void addAuteur(Auteur auteur, int idUniversite){
        AuteurEntity auteurEntity = new AuteurEntity();
        auteurEntity.addAuteur(auteur, idUniversite);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

        // Get elements input
    public String getNomAuteur() {
        return txtNomAuteur.getText();
    }

    public String getPrenomAuteur() {
        return txtPrenomAuteur.getText();
    }

    public String getEmailAuteur() {
        return txtEmailAuteur.getText();
    }

    public String getNomUniversite() {
        return txtNomUniversite.getText();
    }

    public String getSiteUniversite() {
        return txtSiteUniversite.getText();
    }

    public String getAdressUniversite() {
        return txtAdressUniversite.getText();
    }

    public JButton getBtnAuteurExiste() {
        return btnAuteurExiste;
    }

    public JButton getBtnUniversiteExiste() {
        return btnUniversiteExiste;
    }
    
    public void setDisableJTextField(String name, String siteWeb, String adresse) {
        this.txtNomUniversite.setText(name);
        this.txtNomUniversite.setEditable(false);
        this.txtNomUniversite.setBackground(Color.LIGHT_GRAY);
        
        this.txtSiteUniversite.setText(siteWeb);
        this.txtSiteUniversite.setEditable(false);
        this.txtSiteUniversite.setBackground(Color.LIGHT_GRAY);
        
        this.txtAdressUniversite.setText(adresse);
        this.txtAdressUniversite.setEditable(false);
        this.txtAdressUniversite.setBackground(Color.LIGHT_GRAY);
    }
    
    public void setEnableJTextField() {
        this.txtNomUniversite.setText("");
        this.txtNomUniversite.setEditable(true);
        this.txtNomUniversite.setBackground(Color.WHITE);
        
        this.txtSiteUniversite.setText("");
        this.txtSiteUniversite.setEditable(true);
        this.txtSiteUniversite.setBackground(Color.WHITE);
        
        this.txtAdressUniversite.setText("");
        this.txtAdressUniversite.setEditable(true);
        this.txtAdressUniversite.setBackground(Color.WHITE);
    }
    
    public boolean newAuteurIsGood() {
        boolean auteurIsGood = true;
        if(this.getNomAuteur().length() == 0) {
            auteurIsGood = false;
            JOptionPane.showMessageDialog(null, "Le nom de l'auteur ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(this.getPrenomAuteur().length() == 0) {
            auteurIsGood = false;
            JOptionPane.showMessageDialog(null, "Le prénom de l'auteur ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(this.getEmailAuteur().length() == 0) {
            auteurIsGood = false;
            JOptionPane.showMessageDialog(null, "Le-mail de l'auteur ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return auteurIsGood;
    }
    
    public boolean newUniversiteIsGood() {
        boolean universiteIsGood = true;
        if(this.getNomUniversite().length() == 0) {
            universiteIsGood = false;
            JOptionPane.showMessageDialog(null, "Le nom de l'université ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(this.getSiteUniversite().length() == 0) {
            universiteIsGood = false;
            JOptionPane.showMessageDialog(null, "Le site web de l'université ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(this.getAdressUniversite().length() == 0) {
            universiteIsGood = false;
            JOptionPane.showMessageDialog(null, "L'adresse de l'université ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return universiteIsGood;
    }
    
    public void refresh() {
        this.txtNomAuteur.setText("");
        this.txtPrenomAuteur.setText("");
        this.txtEmailAuteur.setText("");
        this.txtNomUniversite.setText("");
        this.txtSiteUniversite.setText("");
        this.txtAdressUniversite.setText("");
    }
}
