package com.yanis.scholar.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Yanis OULHACI
 */
public class ArticlePanel extends JPanel {
    private final JPanel panelPrincipal = new JPanel();
    
    
    private final JTextField txtTitreArticle = new JTextField();
    private final JTextArea txtContenuArticle = new JTextArea(10, 37);
    private final JTextField txtNbPage = new JTextField();
    private static final String typeCheck[] = {"Conférence", "Revue"};
    private JComboBox<String> txtTypeArticle = new JComboBox<>(typeCheck);
    private final JTextField txtNomConferene = new JTextField();
    private final JTextField txtNomRevue = new JTextField();
    private final JTextField txtLieuConference = new JTextField();
    private final JTextField txtFacteurImpact = new JTextField();
    
    private final JLabel labelNomConferene = new JLabel("Nom de la conférence");
    private final JLabel labelNomRevue = new JLabel("Nom de la revue");
    private final JLabel labelLieuConference = new JLabel("Lieux de la conference");
    private final JLabel labelFacteurImpact = new JLabel("Le facteur d'impact");
    

    public ArticlePanel() {
        
        // Les bordure
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        EmptyBorder emptyBorder = new EmptyBorder(2, 15, 15, 15);
        EmptyBorder emptyBorderSecondary = new EmptyBorder(3, 0, 3, 0);
        
        // Les panels
        JPanel pTitleAjouterArticle = new JPanel();
        JPanel pBody = new JPanel();
        
        JPanel sectionArticle = new JPanel(); sectionArticle.setBorder(blackline);
        JPanel sectionAauteur = new JPanel(); sectionAauteur.setBorder(blackline);
        JPanel sectionUniversite = new JPanel(); sectionUniversite.setBorder(blackline);

        
        // Les labels
        JLabel labelTitle = new JLabel("Ajouter un article");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        
            // Section Article
        JLabel labelInformationArticle = new JLabel("Information sur l'article"); labelInformationArticle.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel labelTitreArticle = new JLabel("Titre de l'article");
        JLabel labelContenuArticle = new JLabel("Contenu de l'article");
        JLabel labelNbPage = new JLabel("Nombre de page");
        JLabel labelTypeArticle = new JLabel("Type de l'article");
            typeConfigurationDisable(txtNomRevue, txtFacteurImpact);
            labelFacteurImpact.setEnabled(false);
            labelNomRevue.setEnabled(false);
        
        // Les input
        
            // Section Article
        txtTitreArticle.setColumns(24); txtTitreArticle.setFont(new Font("SansSerif", Font.BOLD, 20));
        txtContenuArticle.setWrapStyleWord(true);
        txtContenuArticle.setLineWrap(true);
        txtNbPage.setColumns(12); txtNbPage.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtNomConferene.setColumns(7); txtNomConferene.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtNomRevue.setColumns(10); txtNomRevue.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtLieuConference.setColumns(6); txtLieuConference.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtFacteurImpact.setColumns(7); txtFacteurImpact.setFont(new Font("SansSerif", Font.PLAIN, 15));
    
        
        // panel grouping element
            
            // Titre de la section article
        JPanel pTitreSectionArticle = new JPanel();
        pTitreSectionArticle.setLayout(new BorderLayout());
        pTitreSectionArticle.add(labelInformationArticle,BorderLayout.WEST);
        pTitreSectionArticle.setBorder(new EmptyBorder(0, 0, 10, 0));
        
            // Titre de l'article
        JPanel pTitreArticle = new JPanel();
        JPanel pLabelTitreArticle = new JPanel();
        pLabelTitreArticle.setLayout(new BorderLayout());
        pLabelTitreArticle.add(labelTitreArticle, BorderLayout.WEST);
        Box b5 = Box.createVerticalBox();
        b5.add(pLabelTitreArticle);
        b5.add(txtTitreArticle);
        pTitreArticle.add(b5);
        
            // Contenu de l'article
        JPanel pContenuArticle = new JPanel();
        JPanel pLabelContenu = new JPanel();
        pLabelContenu.setLayout(new BorderLayout());
        Box b6 = Box.createVerticalBox();
        pLabelContenu.add(labelContenuArticle, BorderLayout.WEST);
        b6.add(pLabelContenu);
        JScrollPane spContenu = new JScrollPane(txtContenuArticle);
        b6.add(spContenu);
        pContenuArticle.add(b6);
        
            // Nombre et type de pages :
        JPanel pNombreType = new JPanel();
        JPanel pLabelTypeArticle = new JPanel();
        pLabelTypeArticle.setLayout(new BorderLayout());
        pLabelTypeArticle.add(labelTypeArticle, BorderLayout.WEST);
        Box b1 = Box.createVerticalBox();
        Box b2 = Box.createVerticalBox();
        b1.add(labelNbPage);
        b1.add(txtNbPage);
        b2.add(pLabelTypeArticle);
        b2.add(txtTypeArticle);
        pNombreType.setLayout(new BorderLayout());
        pNombreType.add(b1, BorderLayout.EAST);
        pNombreType.add(b2, BorderLayout.WEST);
        
            // Nom de la revue et de la conférence
        JPanel pNomRC = new JPanel();
        pNomRC.setBorder(emptyBorderSecondary);
        Box b3 = Box.createVerticalBox();
        Box b4 = Box.createVerticalBox();
        b3.add(labelNomRevue);
        b3.add(txtNomRevue);
        b4.add(labelNomConferene);
        b4.add(txtNomConferene);
        pNomRC.setLayout(new BorderLayout());
        pNomRC.add(b3, BorderLayout.WEST);
        pNomRC.add(b4, BorderLayout.EAST);
        
            // Lieux et facteur impact
        JPanel pLieuFacteur = new JPanel();
        pLieuFacteur.setBorder(emptyBorderSecondary);
        Box b7 = Box.createVerticalBox();
        Box b8 = Box.createVerticalBox();
        b7.add(labelFacteurImpact);
        b7.add(txtFacteurImpact);
        b8.add(labelLieuConference);
        b8.add(txtLieuConference);
        pLieuFacteur.setLayout(new BorderLayout());
        pLieuFacteur.add(b7, BorderLayout.WEST);
        pLieuFacteur.add(b8, BorderLayout.EAST);
        
        // Insertion sur la section article
        pTitleAjouterArticle.add(labelTitle);
        
        Box boxSectionArticle = Box.createVerticalBox();
        boxSectionArticle.setBorder(emptyBorder);
        
        boxSectionArticle.add(pTitreSectionArticle);
        boxSectionArticle.add(pTitreArticle);
        boxSectionArticle.add(pContenuArticle);
        boxSectionArticle.add(pNombreType);
        boxSectionArticle.add(pNomRC);
        boxSectionArticle.add(pLieuFacteur);
        
        // Insertion de la fin
        sectionArticle.add(boxSectionArticle);
        pBody.add(sectionArticle);
        
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(pTitleAjouterArticle, BorderLayout.NORTH);
        panelPrincipal.add(pBody, BorderLayout.CENTER);
        
        txtTypeArticle.addActionListener((ActionEvent e) -> {
            typeConfiguration(txtTypeArticle.getSelectedIndex());
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
        // Les fonctions
    private void typeConfiguration(int ComboIndex) {
        if(ComboIndex == 0) {
            typeConfigurationEnable(txtNomConferene, txtLieuConference);
            typeConfigurationDisable(txtNomRevue, txtFacteurImpact);
            labelFacteurImpact.setEnabled(false);
            labelNomRevue.setEnabled(false);
            labelNomConferene.setEnabled(true);
            labelLieuConference.setEnabled(true);
        } else if(ComboIndex == 1) {
            typeConfigurationDisable(txtNomConferene, txtLieuConference);
            typeConfigurationEnable(txtNomRevue, txtFacteurImpact);
            labelFacteurImpact.setEnabled(true);
            labelNomRevue.setEnabled(true);
            labelNomConferene.setEnabled(false);
            labelLieuConference.setEnabled(false);
        }
    }
    private void typeConfigurationEnable(JTextField textField1, JTextField textField2) {
        textField1.setEnabled(true);
        textField1.setBackground(Color.WHITE);
        textField2.setEnabled(true);
        textField2.setBackground(Color.WHITE);
    }
    private void typeConfigurationDisable(JTextField textField1, JTextField textField2) {
        textField1.setEnabled(false);
        textField1.setBackground(Color.LIGHT_GRAY);
        textField1.setText("");
        textField2.setEnabled(false);
        textField2.setBackground(Color.LIGHT_GRAY);
        textField2.setText("");
    }

        // Get elements input
    public String getTitreArticle() {
        return txtTitreArticle.getText();
    }

    public String getContenuArticle() {
        return txtContenuArticle.getText();
    }

    public int getNbPage() {
        int nbPage;
        try{
            nbPage = Integer.parseInt(txtNbPage.getText());
        } catch(NumberFormatException e) {
            System.err.println("ERROR, Le nombre de page est un entier positive");
            nbPage = -1; 
        }
        return nbPage;
    }

    public int getTypeArticle() {
        return txtTypeArticle.getSelectedIndex();
    }

    public String getNomConferene() {
        return txtNomConferene.getText();
    }

    public String getNomRevue() {
        return txtNomRevue.getText();
    }

    public String getLieuConference() {
        return txtLieuConference.getText();
    }

    public float getFacteurImpact() {
        float facteurImpact;
        try{
            facteurImpact = Float.parseFloat(txtFacteurImpact.getText());
        } catch(NumberFormatException e) {
            System.err.println("ERROR, Le nombre de page est un entier positive");
            facteurImpact = -1;
        }
        return facteurImpact;
    }

    public void refreshPanel() {
        this.txtTitreArticle.setText("");
        this.txtContenuArticle.setText("");
        this.txtNbPage.setText("");
        this.txtNomConferene.setText("");
        this.txtNomRevue.setText("");
        this.txtFacteurImpact.setText("");
        this.txtLieuConference.setText("");
    }
    
    
    
    
}
