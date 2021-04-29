package com.yanis.scholar.Control;

/**
 *
 * @author Yanis OULHACI
 */
public class Revue extends Article{
    private String nomR;
    private float facteurImpact;
    private CheckApostrophe checkApostrophe = new CheckApostrophe();

    public Revue() {
        super();
        this.setNomR("Revue");
        this.setFacteurImpact(0);
    }

    public Revue(String titre, String contenu, int nbPages, String nomR, float facteurImpact) {
        super(titre, contenu, nbPages);
        this.setNomR(nomR);
        this.setFacteurImpact(facteurImpact);
    }
    
    
    
/**
 *
 * Getter and Setter
 */
    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        if(nomR.length() > 30) {
            nomR = nomR.substring(0,25)+"..." ;
        }
        nomR = nomR.substring(0,1).toUpperCase() + nomR.substring(1);
        nomR = checkApostrophe.apostropheConfiguration(nomR);
        this.nomR = nomR;
    }

    public float getFacteurImpact() {
        return facteurImpact;
    }

    public void setFacteurImpact(float facteurImpact) {
        if(facteurImpact > 5) {
            facteurImpact = 5;
        } else if(facteurImpact < 0) {
            facteurImpact = 0;
        }
        this.facteurImpact = facteurImpact;
    }
    

}
