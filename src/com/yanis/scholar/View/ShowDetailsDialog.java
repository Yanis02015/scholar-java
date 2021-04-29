package com.yanis.scholar.View;

import javax.swing.JDialog;
import javax.swing.JFrame;
import com.yanis.scholar.Control.*;
import com.yanis.scholar.Model.AuteurEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
public class ShowDetailsDialog extends JDialog{
    private String textContent;
    private int index;

    public ShowDetailsDialog(JFrame frame, String title, Article article, Conference conference, Revue revue, ArrayList<String> idAuteurSelected, boolean  typeArticle){
        super(frame, title, true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        
        // Les bordure
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        EmptyBorder emptyBorder = new EmptyBorder(2, 15, 15, 15);
        EmptyBorder emptyBorderSecondary = new EmptyBorder(3, 0, 3, 0);
        
        JPanel panelPriLcipal = new JPanel();
        JPanel pTitre = new JPanel();
        JPanel pOK = new JPanel();
        
        JLabel labelTitle = new JLabel("Détail sur l'article");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        pTitre.add(labelTitle);
        
        JButton btnOK = new JButton("OK!");
        pOK.add(btnOK);
        
        JTextArea details = new JTextArea();
        details.setFont(new Font("SansSerif", Font.BOLD, 14));
        details.setEditable(false);
        details.setWrapStyleWord(true);
        details.setLineWrap(true);
        
        textContent = "Titre :\n" + article.getTitre() + ".\n\n";
        textContent = textContent + "Le nombre de page : " + article.getNbPages()+".\n";
        if(typeArticle) {
            textContent = textContent + "Le type d'article : Conférence.\n";
            textContent = textContent + "Le nom de la conférence : " + conference.getNomC()+ "\n";
            textContent = textContent + "Lieu la conférence : " + conference.getLieu()+ "\n";
        } else {
            textContent = textContent + "Le type d'article : Revue.\n";
            textContent = textContent + "Le nom de la revue : " + revue.getNomR() + "\n";
            textContent = textContent + "Le facteur impact de la revue : " + revue.getFacteurImpact() + "\n";
        }
        
        // Auteurs
        AuteurEntity auteurEntity = new AuteurEntity();
        index = 1;
        idAuteurSelected.forEach(string -> {
            Auteur auteurTmp = auteurEntity.getAuteurById(string);
            Universite universite = auteurEntity.getUniversite();
            textContent = textContent + "\n\n";
            textContent = textContent + "Auteur N°" + index +"\n";
            textContent = textContent + "Nom : " + auteurTmp.getNomA() +"\n";
            textContent = textContent + "Prenom : " + auteurTmp.getPrenomA()+"\n";
            textContent = textContent + "E-mail : " + auteurTmp.getEmail()+"\n";
            textContent = textContent + "Université : " + universite.getNom()+"\n";
            textContent = textContent + "Université : " + universite.getNom()+"\n";
            textContent = textContent + "Site Web : " + universite.getSiteweb()+"\n";
            textContent = textContent + "Adresse : " + universite.getAdresse()+"\n";
            index++;
        });
        
        details.setText(textContent);
        JScrollPane scArea = new JScrollPane(details);
        panelPriLcipal.setLayout(new BorderLayout());
        panelPriLcipal.add(scArea, BorderLayout.CENTER);
        panelPriLcipal.add(pTitre, BorderLayout.NORTH);
        panelPriLcipal.add(pOK,BorderLayout.SOUTH);
        panelPriLcipal.setBorder(emptyBorder);
        this.add(panelPriLcipal);
        
        btnOK.addActionListener((e) -> {
           dispose();
        });
    }
    
    public ShowDetailsDialog(JFrame frame, String title, String string) {
        super(frame, title, true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // Les bordure
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        EmptyBorder emptyBorder = new EmptyBorder(2, 15, 15, 15);
        
        JPanel panelPriLcipal = new JPanel();
        JPanel pTitre = new JPanel();
        JPanel pOK = new JPanel();
        
        JLabel labelTitle = new JLabel("A PROPOS");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        pTitre.add(labelTitle);
        
        JButton btnOK = new JButton("OK!");
        pOK.add(btnOK);
        
        JTextArea details = new JTextArea();
        details.setFont(new Font("SansSerif", Font.BOLD, 14));
        details.setEditable(false);
        details.setBackground(Color.LIGHT_GRAY);
        details.setWrapStyleWord(true);
        details.setLineWrap(true);
        
        details.setText(string);
        JScrollPane scArea = new JScrollPane(details);
        panelPriLcipal.setLayout(new BorderLayout());
        panelPriLcipal.add(scArea, BorderLayout.CENTER);
        panelPriLcipal.add(pTitre, BorderLayout.NORTH);
        panelPriLcipal.add(pOK,BorderLayout.SOUTH);
        panelPriLcipal.setBorder(emptyBorder);
        this.add(panelPriLcipal);
        
        btnOK.addActionListener((e) -> {
           dispose();
        });
        
    }
}
