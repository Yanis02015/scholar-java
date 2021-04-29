package com.yanis.scholar.View;

import com.yanis.scholar.Model.AuteurEntity;
import com.yanis.scholar.Model.UniversiteEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yanis OULHACI
 */
public class SelectUniversitePanel extends JPanel{
    private JScrollPane scPrincipal;
    private JPanel panelPrincipal;
    private String idUniversiteSelected = "-1";
    private JButton btnAddNewUniversite = new JButton("Créer une nouvlle université");
    private JTable table;
    private UniversiteEntity universiteEntity = new UniversiteEntity();
    private DefaultTableModel tModel;
    
    private String valueName;
    private String valueWebSite;
    private String valueAdresse;
    
    private JTextArea txtUniversiteSelected;
     JButton btnUniversiteSelected;
    
    public SelectUniversitePanel() {
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        JPanel pTitleSelectUniversite = new JPanel();
        
        // Les labels
        JLabel labelTitle = new JLabel("Selectionner l'université");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        pTitleSelectUniversite.add(labelTitle);
        
        String[] header = {"id", "Nom", "Site web", "Adresse"};
        tModel = universiteEntity.getTableModel(header);
        table = new JTable(tModel) {
            // Pour désactiver l'édition des cellules dans la JTable (Source : https://www.tutorialspoint.com/how-can-we-disable-the-cell-editing-inside-a-jtable-in-java#:~:text=By%20default%2C%20we%20can%20edit,and%20it%20must%20return%20false. )
            public boolean  editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        scPrincipal = new JScrollPane(table);
        scPrincipal.setBorder(new EmptyBorder(5, 15, 0, 15));
        
        // ------ La parti improvisé :
        
        String selectUniv = "Selectionner l'université";
        btnUniversiteSelected = new JButton(selectUniv);
        JPanel pAddUniversiteSelected = new JPanel();
        pAddUniversiteSelected.add(btnUniversiteSelected);
        
        JPanel pAddNewUniversite = new JPanel();
        pAddNewUniversite.add(btnAddNewUniversite);
        
        JPanel pAddUniversite = new JPanel();
        pAddUniversite.setLayout(new BorderLayout());
        pAddUniversite.add(pAddNewUniversite, BorderLayout.WEST);
        pAddUniversite.add(pAddUniversiteSelected, BorderLayout.EAST);
        pAddUniversite.setBorder(new EmptyBorder(3, 10, 5, 10));
        
        txtUniversiteSelected = new JTextArea(10, 41);
        txtUniversiteSelected.setEditable(false);
        txtUniversiteSelected.setBackground(Color.lightGray);
        JScrollPane scAuteurSelected = new JScrollPane(txtUniversiteSelected);
        JPanel pUniversiteSelected = new JPanel();
        pUniversiteSelected.add(scAuteurSelected);
        
        JButton btnClearUniversite = new JButton("Refaire la selection");
        JPanel pCleanUniversite = new JPanel();
        pCleanUniversite.setLayout(new BorderLayout());
        pCleanUniversite.add(btnClearUniversite, BorderLayout.EAST);
        pCleanUniversite.setBorder(new EmptyBorder(0, 0, 0, 15));
        
        JPanel pAreaClean = new JPanel();
        pAreaClean.setLayout(new BorderLayout());
        pAreaClean.add(pUniversiteSelected, BorderLayout.CENTER);
        pAreaClean.add(pCleanUniversite, BorderLayout.SOUTH);
        
        JPanel pBottom = new JPanel();
        pBottom.setLayout(new BorderLayout());
        pBottom.add(pAddUniversite, BorderLayout.NORTH);
        pBottom.add(pAreaClean, BorderLayout.CENTER);
        
        // ------------------------------------------
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(scPrincipal, BorderLayout.CENTER);
        panelPrincipal.add(pTitleSelectUniversite, BorderLayout.NORTH);
        panelPrincipal.add(pBottom, BorderLayout.SOUTH);
        
        // -------------- ActionListener des button
        
        btnUniversiteSelected.addActionListener((ActionEvent e) -> {
            int indexRowSelected = table.getSelectedRow();
            if(indexRowSelected != -1) {
                String valueId = table.getModel().getValueAt(indexRowSelected, 0).toString();
                valueName = table.getModel().getValueAt(indexRowSelected, 1).toString();
                valueWebSite = table.getModel().getValueAt(indexRowSelected, 2).toString();
                valueAdresse = table.getModel().getValueAt(indexRowSelected, 3).toString();
                System.out.println(valueName + " " + valueWebSite + " " + valueAdresse);
                btnUniversiteSelected.setEnabled(false);
                idUniversiteSelected = valueId;
                txtUniversiteSelected.setText(txtUniversiteSelected.getText() + valueName + " " + valueWebSite + ", " + valueAdresse + ".\n");
            }
        });
        
        btnClearUniversite.addActionListener(((e) -> {
            this.refresh();
        }));
    }
    
    
    // Les méthodes

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    public JScrollPane getScPrincipal() {
        return scPrincipal;
    }

    public JTable getTable() {
        return table;
    }

    public void updateModel() {
        String[] header = {"id", "Nom", "Site web", "Adress"};
        tModel.setRowCount(0);
        tModel = universiteEntity.getTableModel(header);
        table.setModel(tModel);
    }

    public String getValueName() {
        return valueName;
    }

    public String getValueWebSite() {
        return valueWebSite;
    }

    public String getValueAdresse() {
        return valueAdresse;
    }

    boolean idUniversiteIsGood() {
        boolean isGood = false;
        if(this.idUniversiteSelected.equals("-1")) {
            isGood = false;
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner au moins un auteur.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else
            isGood = true;
        return isGood;
    }

    public String getIdUniversiteSelected() {
        return idUniversiteSelected;
    }

    public JButton getBtnAddNewUniversite() {
        return btnAddNewUniversite;
    }

    public void refresh() {
        idUniversiteSelected = "-1";
        txtUniversiteSelected.setText("");
        btnUniversiteSelected.setEnabled(true);
    }
    
}