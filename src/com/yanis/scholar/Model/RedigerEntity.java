package com.yanis.scholar.Model;

import com.yanis.scholar.Control.Auteur;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.yanis.scholar.Control.Article;
import com.yanis.scholar.Control.Conference;
import com.yanis.scholar.Control.Revue;
import com.yanis.scholar.View.SelectAuteurPanel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yanis OULHACI
 */
public class RedigerEntity {
    private final Connexion connexion = new Connexion();
    
    public void addRedaction(ArrayList<String> idAuteur ,Article article) {
        // Se connecter :
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        ArticleEntity articleEntity = new ArticleEntity();
        int idArticle = articleEntity.getIdArticle(article);
        
        // Insertion dans la base de données
        try {
            for(String id : idAuteur) {
                System.out.printf("INSERT INTO `rediger`(`idAuteur`, `idArticle`) VALUES (" + id + "," + idArticle + ")");
                statement.execute("INSERT INTO `rediger`(`idAuteur`, `idArticle`) VALUES (" + id + "," + idArticle + ")");
            }
            connexion.close();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de Connexion", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }
    
    public ArrayList<String> getAuteurList(String idArticle) {
        // Se connecter :
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        ArrayList<String> idAuteurList = new ArrayList<>();
        
        try {
            ResultSet result = statement.executeQuery("SELECT idAuteur FROM rediger WHERE idArticle =" + idArticle);
            result.beforeFirst();
            while(result.next()) {
                idAuteurList.add(result.getObject(1).toString());
            }
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idAuteurList;
    }
}
