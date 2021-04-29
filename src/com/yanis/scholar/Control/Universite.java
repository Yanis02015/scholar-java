package com.yanis.scholar.Control;

/**
 *
 * @author Yanis OULHACI
 */
public class Universite {
    private String nom;
    private String siteweb;
    private String adresse;
    private CheckApostrophe checkApostrophe = new CheckApostrophe();

    public Universite() {
        this.setNom("Université");
        this.setSiteweb("Pas de site web");
        this.setAdresse("Inconnu");
    }

    public Universite(String nom, String siteweb, String adresse) {
        this.setNom(nom);
        this.setSiteweb(siteweb);
        this.setAdresse(adresse);
    }


/**
 *
 * Getter and Setter
 */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom.length() > 50) {
            nom = nom.substring(0,45)+"..." ;
        }
        nom = nom.substring(0,1).toUpperCase() + nom.substring(1);
        nom = checkApostrophe.apostropheConfiguration(nom);
        this.nom = nom;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        if(siteweb.length() > 50) {
            siteweb = siteweb.substring(0,45)+"..." ;
        }
        siteweb = checkApostrophe.apostropheConfiguration(siteweb);
        this.siteweb = siteweb;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        if(adresse.length() > 140) {
            adresse = adresse.substring(0,135)+"..." ;
        }
        adresse = checkApostrophe.apostropheConfiguration(adresse);
        this.adresse = adresse;
    }
}