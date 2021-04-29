package com.yanis.scholar.View;

import com.yanis.scholar.Model.ArticleEntity;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yanis OULHACI
 */
public class ShowAllArticle extends JPanel{
    private JPanel panelPrincipal = new JPanel();
    private DefaultTableModel defaultTableModel;
    private ArticleEntity articleEntity = new ArticleEntity();
    private JTable table;
    private JButton btnShowArticleSelected;

    public ShowAllArticle() {
        // Les bordures
        Border underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        
        // Les labels
        JPanel pTitre = new JPanel();
        JLabel labelTitle = new JLabel("Liste des articles");
        labelTitle.setFont(new Font("SansSerif", Font.TRUETYPE_FONT, 20));
        labelTitle.setBorder(underline);
        pTitre.add(labelTitle);
        
        // La table
        String[] title = {"Id", "Titre", "Type", "Nombre de page"};
        defaultTableModel = articleEntity.getTableModel(title);
        table = new JTable(defaultTableModel) {
            // Pour désactiver l'édition des cellules dans la JTable (Source : https://www.tutorialspoint.com/how-can-we-disable-the-cell-editing-inside-a-jtable-in-java#:~:text=By%20default%2C%20we%20can%20edit,and%20it%20must%20return%20false. )
            @Override
            public boolean  editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        JScrollPane sc = new JScrollPane(table);
        
        // Jbutton show article
        JPanel pShowArticleSelected = new JPanel();
        btnShowArticleSelected = new JButton("Afficher l'article");
        pShowArticleSelected.add(btnShowArticleSelected);
        
        
        
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(pTitre, BorderLayout.NORTH);
        panelPrincipal.add(sc, BorderLayout.CENTER);
        panelPrincipal.add(pShowArticleSelected, BorderLayout.SOUTH);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    public void updateShowAllArticle() {
        String[] title = {"Id", "Titre", "Type", "Nombre de page"};
        defaultTableModel.setRowCount(0);
        defaultTableModel = articleEntity.getTableModel(title);
        table.setModel(defaultTableModel);
    }

    public JButton getBtnShowArticleSelected() {
        return btnShowArticleSelected;
    }

    public String getTableIdSelected() {
        int indexRowSelected = table.getSelectedRow();
        String idSelected = table.getModel().getValueAt(indexRowSelected, 0).toString();
        return idSelected;
    }
    public String getTableTypeSelected() {
        int indexRowSelected = table.getSelectedRow();
        String typeSelected = table.getModel().getValueAt(indexRowSelected, 2).toString();
        return typeSelected;
    }
    
}
