package com.yanis.scholar.Model;


import com.yanis.scholar.Control.Universite;
import com.yanis.scholar.Control.Auteur;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yanis OULHACI
 */
public class AuteurEntity {
    private final Connexion connexion = new Connexion();
    private Universite universite;
    
    public void addAuteur(Auteur auteur, int univId) {
        // Se connecter :
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        // Insertion dans la base de données
        try {
            System.out.printf("INSERT INTO `auteur`(`nomA`, `prenomA`, `email`, `idUniversite`) VALUES ('" + auteur.getNomA() + "','" + auteur.getPrenomA() + "','" + auteur.getEmail() + "','" + univId + "');");
            statement.execute("INSERT INTO `auteur`(`nomA`, `prenomA`, `email`, `idUniversite`) VALUES ('" + auteur.getNomA() + "','" + auteur.getPrenomA() + "','" + auteur.getEmail() + "','" + univId + "');");

        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de Connexion", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
        
        connexion.close();
    }
    
    public DefaultTableModel getTableModel(String[] title) {
        DefaultTableModel tModel = new DefaultTableModel(title, 0);
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        //On récupère les MetaData
        try {
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = statement.executeQuery("SELECT * FROM auteur");
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
    
    public int getIdAuteur(Auteur auteur) {
        int getIdAuteur;
        String string = "1";
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        try {
            ResultSet result = statement.executeQuery("SELECT idAuteur FROM auteur WHERE nom='" + auteur.getNomA() +"' AND prenom='" + auteur.getPrenomA() + "' AND email='" + auteur.getEmail() +"'");
            result.last();
            Object obj = result.getObject(1);
            string = obj.toString();
            
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            getIdAuteur = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            getIdAuteur=1;
        }
        
        return getIdAuteur;
    }
    
    public Auteur getAuteurById(String id) {
        Auteur auteur = new Auteur();
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        try {
            ResultSet result = statement.executeQuery("SELECT nomA, prenomA, email, nom, siteweb, adresse FROM auteur, universite WHERE idAuteur=" + id + " AND auteur.idUniversite = universite.idUniversite");
            result.last();
            String nom = result.getObject(1).toString();
            String prenom = result.getObject(2).toString();
            String email = result.getObject(3).toString();
            auteur = new Auteur(nom, prenom, email);
                    
            String nomUniversite = result.getObject(4).toString();
            String siteweb = result.getObject(5).toString();
            String adresse = result.getObject(6).toString();
            this.universite = new Universite(nomUniversite, siteweb, adresse);
            
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auteur;
    }

    public Universite getUniversite() {
        return universite;
    }
    
    
}