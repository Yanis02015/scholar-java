/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yanis.scholar.Model;

import com.yanis.scholar.Control.Universite;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yanis OULHACI
 */
public class UniversiteEntity {
    private final Connexion connexion = new Connexion();
    
    public void addUniversite(Universite universite) {
        // Se connecter :
        connexion.seConnecter();
        Statement statement;
        ResultSet resultSet;
        statement = connexion.getStmt();
        
        // Insertion dans la base de données
        try {
            System.out.printf("INSERT INTO `universite`(`nom`, `siteweb`, `adresse`) VALUES ('" + universite.getNom() + "','" + universite.getSiteweb() + "','" + universite.getAdresse() + "');");
            statement.execute("INSERT INTO `universite`(`nom`, `siteweb`, `adresse`) VALUES ('" + universite.getNom() + "','" + universite.getSiteweb() + "','" + universite.getAdresse() + "');");
            
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
            ResultSet result = statement.executeQuery("SELECT * FROM universite");
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

    public int getIdUniversite(Universite universite) {
        int getIdUniversite;
        String string = "1";
        connexion.seConnecter();
        Statement statement;
        statement = connexion.getStmt();
        
        try {
            ResultSet result = statement.executeQuery("SELECT idUniversite FROM universite WHERE nom='"+ universite.getNom() +"' AND siteweb='" + universite.getSiteweb() + "' AND adresse='" + universite.getAdresse() + "'");
            result.last();
            Object obj = result.getObject(1);
            string = obj.toString();
            
            result.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AuteurEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            getIdUniversite = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            getIdUniversite=1;
        }
        
        return getIdUniversite;
    }
}
