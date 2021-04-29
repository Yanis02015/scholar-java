package com.yanis.scholar.Control;

/**
 *
 * @author Yanis OULHACI
 */
public class Conference extends Article{
    private String nomC;
    private String lieu;
    private CheckApostrophe checkApostrophe = new CheckApostrophe();

    public Conference() {
        super();
        this.setNomC("Inconnu");
        this.setLieu("Inconnu");
    }

    public Conference(String titre, String contenu, int nbPages, String nomC, String lieu) {
        super(titre, contenu, nbPages);
        this.setNomC(nomC);
        this.setLieu(lieu);
    }
    
    
    
/**
 *
 * Getter and Setter
 */
    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        if(nomC.length() > 30) {
            nomC = nomC.substring(0,25)+"..." ;
        }
        nomC = nomC.substring(0,1).toUpperCase() + nomC.substring(1);
        nomC = checkApostrophe.apostropheConfiguration(nomC);
        this.nomC = nomC;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        if(lieu.length() > 50) {
            lieu = lieu.substring(0,45)+"..." ;
        }
        lieu = lieu.substring(0,1).toUpperCase() + lieu.substring(1);
        lieu = checkApostrophe.apostropheConfiguration(lieu);
        this.lieu = lieu;
    }


    
}
