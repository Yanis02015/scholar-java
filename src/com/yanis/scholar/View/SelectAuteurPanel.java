package com.yanis.scholar.View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.yanis.scholar.Model.AuteurEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yanis OULHACI
 */
public class SelectAuteurPanel extends JPanel{
    private JScrollPane scPrincipal;
    private JPanel panelPrincipal;
    private ArrayList<String> idAuteurSelected = new ArrayList<>();
    private JButton btnAddNewAuteur = new JButton("Créer un nouvel auteur");
    private JTable table;
    private AuteurEntity auteurEntity = new AuteurEntity();
    private DefaultTableModel tModel;
    private JTextArea txtAuteurSelected;
    
    public SelectAuteurPanel() {
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        JPanel pTitleSelectAuteur = new JPanel();
        //pTitleSelectAuteur.setLayout(new BorderLayout());
        
        // Les labels
        JLabel labelTitle = new JLabel("Selectionner les auteurs");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        pTitleSelectAuteur.add(labelTitle);
        
        String[] header = {"id", "Nom", "Prénom", "E-mail", "Id de l'université"};
        tModel = auteurEntity.getTableModel(header);
        table = new JTable(tModel) {
            // Pour désactiver l'édition des cellules dans la JTable (Source : https://www.tutorialspoint.com/how-can-we-disable-the-cell-editing-inside-a-jtable-in-java#:~:text=By%20default%2C%20we%20can%20edit,and%20it%20must%20return%20false. )
            @Override
            public boolean  editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        scPrincipal = new JScrollPane(table);
        scPrincipal.setBorder(new EmptyBorder(5, 15, 0, 15));
        
        // ------ La parti improvisé :
        
        JButton btnAddAuteurSelected = new JButton("Ajouter l'auteur");
        JPanel pAddAuteurSelected = new JPanel();
        pAddAuteurSelected.add(btnAddAuteurSelected);
        
        JPanel pAddNewAuteur = new JPanel();
        pAddNewAuteur.add(btnAddNewAuteur);
        
        JPanel pAddAuteur = new JPanel();
        pAddAuteur.setLayout(new BorderLayout());
        pAddAuteur.add(pAddNewAuteur, BorderLayout.WEST);
        pAddAuteur.add(pAddAuteurSelected, BorderLayout.EAST);
        pAddAuteur.setBorder(new EmptyBorder(3, 10, 5, 10));
        
        txtAuteurSelected = new JTextArea(10, 41);
        txtAuteurSelected.setEditable(false);
        txtAuteurSelected.setBackground(Color.lightGray);
        JScrollPane scAuteurSelected = new JScrollPane(txtAuteurSelected);
        JPanel pAuteurSelected = new JPanel();
        pAuteurSelected.add(scAuteurSelected);
        
        JButton btnClearAuteur = new JButton("Refaire la selection");
        JPanel pCleanAuteur = new JPanel();
        pCleanAuteur.setLayout(new BorderLayout());
        pCleanAuteur.add(btnClearAuteur, BorderLayout.EAST);
        pCleanAuteur.setBorder(new EmptyBorder(0, 0, 0, 15));
        
        JPanel pAreaClean = new JPanel();
        pAreaClean.setLayout(new BorderLayout());
        pAreaClean.add(pAuteurSelected, BorderLayout.CENTER);
        pAreaClean.add(pCleanAuteur, BorderLayout.SOUTH);
        
        JPanel pBottom = new JPanel();
        pBottom.setLayout(new BorderLayout());
        pBottom.add(pAddAuteur, BorderLayout.NORTH);
        pBottom.add(pAreaClean, BorderLayout.CENTER);
        
        // ------------------------------------------
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(scPrincipal, BorderLayout.CENTER);
        panelPrincipal.add(pTitleSelectAuteur, BorderLayout.NORTH);
        panelPrincipal.add(pBottom, BorderLayout.SOUTH);
        
        // -------------- ActionListener des button
        
        btnAddAuteurSelected.addActionListener((ActionEvent e) -> {
            int indexRowSelected = table.getSelectedRow();
            String valueId = table.getModel().getValueAt(indexRowSelected, 0).toString();
            if(indexRowSelected != -1 && !idAuteurSelected.contains(valueId)) {
                String valueName = table.getModel().getValueAt(indexRowSelected, 1).toString();
                String valueLastname = table.getModel().getValueAt(indexRowSelected, 2).toString();
                String valueEmail = table.getModel().getValueAt(indexRowSelected, 3).toString();
                idAuteurSelected.add(valueId);
                txtAuteurSelected.setText(txtAuteurSelected.getText() + valueName + " " + valueLastname + ", " + valueEmail + ".\n");
                System.out.println(idAuteurSelected.size());
            }
        });
        
        btnClearAuteur.addActionListener(((e) -> {
            this.refresh();
        }));
    }
    
    
    // Les méthodes
    
    public JScrollPane getScPrincipal() {
        return scPrincipal;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public ArrayList<String> getIdAuteurSelected() {
        return idAuteurSelected;
    }
    
    public boolean idAuteurIsGood() {
        boolean isGood = true;
        if(this.idAuteurSelected.size() <= 0) {
            isGood = false;
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner au moins un auteur.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
        return isGood;
    }

    public JButton getBtnAddNewAuteur() {
        return btnAddNewAuteur;
    }

    public JTable getTable() {
        return table;
    }

    public AuteurEntity getAuteurEntity() {
        return auteurEntity;
    }

    public void updateModel() {
        String[] header = {"id", "Nom", "Prénom", "E-mail", "Id de l'université"};
        tModel.setRowCount(0);
        tModel = auteurEntity.getTableModel(header);
        table.setModel(tModel);
    }

    public void refresh() {
        idAuteurSelected.clear();
        txtAuteurSelected.setText("");
    }
    
}