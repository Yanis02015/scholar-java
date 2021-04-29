package com.yanis.scholar.Control;

/**
 *
 * @author Yanis OULHACI
 */
public class Auteur {
    private String nomA;
    private String prenomA;
    private String email;
    private CheckApostrophe checkApostrophe = new CheckApostrophe();

    public Auteur() {
        this.setNomA("Inconnu");
        this.setPrenomA("Inconnu");
        this.setEmail("Null");
    }

    public Auteur(String nomA, String prenomA, String email) {
        this.setNomA(nomA);
        this.setPrenomA(prenomA);
        this.setEmail(email);
    }
    
/**
 *
 * Getter and Setter
 */
    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        if(nomA.length() > 50) {
            nomA = nomA.substring(0,45)+"..." ;
        }
        nomA = nomA.substring(0,1).toUpperCase() + nomA.substring(1);
        nomA = checkApostrophe.apostropheConfiguration(nomA);
        this.nomA = nomA;
    }

    public String getPrenomA() {
        return prenomA;
    }

    public void setPrenomA(String prenomA) {
        if(prenomA.length() > 50) {
            prenomA = prenomA.substring(0,45)+"..." ;
        }
        prenomA = prenomA.substring(0,1).toUpperCase() + prenomA.substring(1);
        prenomA = checkApostrophe.apostropheConfiguration(prenomA);
        this.prenomA = prenomA;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // TODO : Vérification
        email = checkApostrophe.apostropheConfiguration(email);
        this.email = email;
    }
    
}