package com.yanis.scholar.Control;

import java.util.ArrayList;

/**
 *
 * @author Yanis OULHACI
 */
public class Article {
    private String titre;
    private int nbPages;
    private String contenu;
    private CheckApostrophe checkApostrophe = new CheckApostrophe();
    
    public Article() {
        this.setTitre("Inconnu");
        this.setNbPages(1);
        this.setContenu("Inconnu");
    }

    public Article(String titre, String contenu, int nbPages) {
        this.setTitre(titre);
        this.setNbPages(nbPages);
        this.setContenu(contenu);
    }
    
    public boolean plagiat(ArrayList<String> arrayList) {
        boolean plagiat = false;
        for(String string : arrayList) {
            if(this.contenu.equals(string))
                plagiat = true;
        }
        return plagiat;
    }
    
/**
 *
 * Getter and Setter
 */
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        // Couper le titre si celui-ci est superieur à 90 (source : https://stackoverflow.com/questions/17685977/cut-java-string-at-a-number-of-character )
        if(titre.length() > 90) {
            titre = titre.substring(0, 89) + "...";
        }
        
        // Mettre la premiere lette du titre en majuscule (source : https://attacomsian.com/blog/capitalize-first-letter-of-string-java?amp )
        titre = titre.substring(0, 1).toUpperCase() + titre.substring(1);
        titre = checkApostrophe.apostropheConfiguration(titre);
        this.titre = titre;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        if(nbPages>32) {
            nbPages = 32;
        } else if(nbPages<1) {
            nbPages = 1;
        }
        this.nbPages = nbPages;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = this.cutContenu(contenu);
    }
    
    public String cutContenu(String contenu) {
        // Compter le nombre de mot du contenu (source : https://www.java67.com/2016/09/3-ways-to-count-words-in-java-string.html?m=1#:~:text=You%20can%20count%20words%20in,words%20in%20a%20given%20String. )
        String[] word = contenu.split("\\s+");
        if(word.length > 7000) {
            // Trouver la position du 7000ème mot
            int position7000 = contenu.indexOf(word[7000]);
            
            // Couper la chaine de caractere
            contenu = contenu.substring(0, position7000) + "...";
        }
        contenu = contenu.substring(0,1).toUpperCase() + contenu.substring(1);
        contenu = checkApostrophe.apostropheConfiguration(contenu);
        return contenu;
    }
    
    
}
