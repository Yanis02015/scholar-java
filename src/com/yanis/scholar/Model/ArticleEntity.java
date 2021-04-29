package com.yanis.scholar.Model;

import com.yanis.scholar.Control.Article;
import com.yanis.scholar.Control.Auteur;
import com.yanis.scholar.Control.Conference;
import com.yanis.scholar.Control.Revue;
import com.yanis.scholar.Control.Universite;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yanis OULHACI
 */
public class ArticleEntity {
    private final Connexion connexion = new Connexion();
    private Revue revueToGet;
    private Conference conferenceToGet;
    
    
    // Ajouter une Conférence
    public void addArticle(Conference conference) {
        
        // Se connecter :
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        // Insertion dans la base de données
        try {
            System.out.println("Préparation de l'ajout");
            System.out.println("INSERT INTO `article`(`titre`, `nbPages`, `contenu`, `type`, `nomC`, `lieu`) VALUES ('" + conference.getTitre() + "'," + conference.getNbPages() + ",'" + conference.getContenu() + "', 'Conférence','" + conference.getNomC() + "','" + conference.getLieu() + "');");
            statement.execute("INSERT INTO `article`(`titre`, `nbPages`, `contenu`, `type`, `nomC`, `lieu`) VALUES ('" + conference.getTitre() + "'," + conference.getNbPages() + ",'" + conference.getContenu() + "', 'Conférence','" + conference.getNomC() + "','" + conference.getLieu() + "');");
            
            JOptionPane.showMessageDialog(null, "Article ajoutée avec succès", "Message de succès", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ajout réussi");
            
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de Connexion", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
        
        connexion.close();
    }
    
    // Ajouter une Revue
    public void addArticle(Revue revue) {
        
        // Se connecter :
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        // Insertion dans la base de données
        try {
            System.out.printf("INSERT INTO `article`(`titre`, `nbPages`, `contenu`, `type`, `nomR`, `facteurImpact`) VALUES ('" + revue.getTitre() + "'," + revue.getNbPages() +",'" + revue.getContenu() + "','Revue','" + revue.getNomR() + "'," + revue.getFacteurImpact() + ");");
            statement.execute("INSERT INTO `article`(`titre`, `nbPages`, `contenu`, `type`, `nomR`, `facteurImpact`) VALUES ('" + revue.getTitre() + "'," + revue.getNbPages() +",'" + revue.getContenu() + "','Revue','" + revue.getNomR() + "'," + revue.getFacteurImpact() + ");");
            
            JOptionPane.showMessageDialog(null, "Article ajoutée avec succès", "Message de succès", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ajout réussi");
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de Connexion", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
        
        connexion.close();
    }
    
    public int getIdArticle(Article article) {
        int getIdArticle;
        String string = "1";
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        try {
            System.out.println("\nSELECT idArticle FROM article WHERE titre='" + article.getTitre() + "' AND nbPages=" + article.getNbPages() + " AND contenu='" + article.getContenu() + "'");
            ResultSet result = statement.executeQuery("SELECT idArticle FROM article WHERE titre='" + article.getTitre() + "' AND nbPages=" + article.getNbPages() + " AND contenu='" + article.getContenu() + "'");
            result.last();
            Object obj = result.getObject(1);
            string = obj.toString();
            
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            getIdArticle = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            getIdArticle=1;
        }
        
        return getIdArticle;
    }
    
    public ArrayList<String> getContenuList() {
        ArrayList<String> contenuList = new ArrayList<>();
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        try {
            System.out.println("\nSELECT contenu FROM article WHERE 1");
            ResultSet result = statement.executeQuery("SELECT contenu FROM article WHERE 1");
            result.beforeFirst();
            while (result.next()) {
            contenuList.add(String.valueOf(result.getObject(1)));
            }
            
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contenuList;
    }
    
    public DefaultTableModel getTableModel(String[] title) {
        DefaultTableModel tModel = new DefaultTableModel(title, 0);
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        //On récupère les MetaData
        try {
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = statement.executeQuery("SELECT idArticle, titre, type, nbPages FROM article");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            while(result.next()) {
                Object[] obj = new Object[resultMeta.getColumnCount()];
                for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                    obj[i-1] = result.getObject(i);
                }
                tModel.addRow(obj);
            }
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AuteurEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tModel;
    }
    
    public Article getArticleById(String id, boolean typeArticle) {
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        Revue revue;
        Conference confrence;
        Article a = new Article();
        
        try {
            ResultSet result = statement.executeQuery("SELECT titre, nbPages, contenu, type, nomR, facteurImpact, nomC, lieu FROM article WHERE idArticle=" + id);
            result.first();
            String titre = result.getObject(1).toString();
            String nbPages = result.getObject(2).toString();
            String contenu = result.getObject(3).toString();
            String type = result.getObject(4).toString();
            System.out.println(titre + " - " + nbPages + " - " + type);
            
            if(!typeArticle) {
                String nomR = result.getObject(5).toString();
                String facteurImpact = result.getObject(6).toString();
                revue = new Revue(titre, contenu, Integer.parseInt(nbPages), nomR, Float.parseFloat(facteurImpact));
                revueToGet = revue;
                a = revue;
            } else{
                System.out.print("tst");
                String nomC = result.getObject(7).toString();
                String lieu = result.getObject(8).toString();
                System.out.println(titre + " - " + lieu + " - " + nomC);
                confrence = new Conference(titre, contenu, Integer.parseInt(nbPages), nomC, lieu);
                conferenceToGet = confrence;
                a = confrence;
            }
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public Revue getRevue() {
        return revueToGet;
    }

    public Conference getConference() {
        return conferenceToGet;
    }
}
