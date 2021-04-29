package com.yanis.scholar.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Yanis OULHACI
 */
public class ShowArticlePanel extends JPanel{
    private final JPanel panelPrincipal = new JPanel();
    
    private JLabel labelTitreArticle = new JLabel("1. Titre de l'article");
    private JTextArea txtContenuArticle = new JTextArea("",22,40);
    
    private JLabel labelTypeArticle = new JLabel("Type : Conference");
    private JLabel labelNomType = new JLabel("Conference à targa");
    
    private JLabel labelNbPageArticle = new JLabel("Nombre de page : 32");
    private JLabel labelNombreMot = new JLabel("Nombre de mots : 7000");
    
    private JButton btnShowDetails = new JButton("Voir plus de detail..");
    
    public ShowArticlePanel() {
        // Les bordure
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        EmptyBorder emptyBorder = new EmptyBorder(2, 15, 15, 15);
        EmptyBorder emptyBorderSecondary = new EmptyBorder(5, 0, 3, 0);
        
            // Les Panel
        JPanel pTitreArticle = new JPanel();
        JPanel pContenuArticle = new JPanel();
        JPanel pTypeArticle = new JPanel();
        JPanel pNbPageArticle = new JPanel();
        JPanel pNomType = new JPanel();
        JPanel pNombreMot = new JPanel();
        
        JPanel pShowDetails = new JPanel();
        
        labelTitreArticle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitreArticle.setBorder(underline);
        
        txtContenuArticle.setEditable(false);
        txtContenuArticle.setBackground(Color.LIGHT_GRAY);
        txtContenuArticle.setColumns(40);
        txtContenuArticle.setWrapStyleWord(true);
        txtContenuArticle.setLineWrap(true);
        
            // Inserer dans les panel
        pTitreArticle.add(labelTitreArticle);
        JScrollPane spContenu = new JScrollPane(txtContenuArticle);
        spContenu.setBackground(null);
        pContenuArticle.add(spContenu);
        
        pTypeArticle.add(labelTypeArticle);
        pNomType.add(labelNomType);
        pNbPageArticle.add(labelNbPageArticle);
        pNombreMot.add(labelNombreMot);
        
        Box boxInfoArticle = Box.createVerticalBox();
        boxInfoArticle.add(pTypeArticle);
        boxInfoArticle.add(pNomType);
        
        pShowDetails.setLayout(new FlowLayout());
        pShowDetails.setBorder(new EmptyBorder(18, 0, 0, 0));
        pShowDetails.add(btnShowDetails);
        
        Box boxInfoContenu = Box.createVerticalBox();
        boxInfoContenu.add(pNbPageArticle);
        boxInfoContenu.add(pNombreMot);
        
        JPanel a = new JPanel();
        a.setLayout(new BorderLayout());
        a.add(boxInfoArticle, BorderLayout.WEST);
        a.add(boxInfoContenu, BorderLayout.EAST);
        a.add(pShowDetails, BorderLayout.CENTER);
        
        Box box = Box.createVerticalBox();
        box.add(pTitreArticle);
        box.add(pContenuArticle);
        box.add(a);
        
        panelPrincipal.add(box);
    }
    
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JLabel getTitreArticle() {
        return labelTitreArticle;
    }

    public JTextArea getContenuArticle() {
        return txtContenuArticle;
    }

    public JLabel getTypeArticle() {
        return labelTypeArticle;
    }

    public JLabel getNbPageArticle() {
        return labelNbPageArticle;
    }

    public JLabel getNomType() {
        return labelNomType;
    }

    public JLabel getNombreMot() {
        return labelNombreMot;
    }

    public JButton getShowDetails() {
        return btnShowDetails;
    }
}
