package com.yanis.scholar.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.yanis.scholar.Control.Article;
import com.yanis.scholar.Control.Auteur;
import com.yanis.scholar.Control.Universite;
import com.yanis.scholar.Control.Conference;
import com.yanis.scholar.Control.Revue;
import com.yanis.scholar.Model.ArticleEntity;
import com.yanis.scholar.Model.AuteurEntity;
import com.yanis.scholar.Model.RedigerEntity;
import com.yanis.scholar.Model.UniversiteEntity;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Yanis OULHACI
 */
public class Fenetre extends JFrame{
    private int indice = 1;
    
        // Les objets Panel().CardLayout
    private ArticlePanel articlePanel = new ArticlePanel();
    private SelectAuteurPanel selectAuteurPanel = new SelectAuteurPanel();
    private SelectUniversitePanel selectUniversitePanel = new SelectUniversitePanel();
    private AuteurPanel auteurPanel = new AuteurPanel();
    private ShowArticlePanel showArticlePanel = new ShowArticlePanel();
    private ShowAllArticle showAllArticle = new ShowAllArticle();
    
        // Les objets Model pour la communication avec la base de données
    private ArticleEntity articleEntity = new ArticleEntity();
    private AuteurEntity auteurEntity = new AuteurEntity();
    private UniversiteEntity universiteEntity = new UniversiteEntity();
    private RedigerEntity redigerEntity = new RedigerEntity();
    
        // Les objets Control pour le control de donnée et les aventage de la POO
    private Article article;
    private Auteur auteur;
    private Universite universite;
    
    private Conference conference;
    private Revue revue;
    private ShowDetailsDialog showDetailsDialog;
    
        // Les element du JMenuBar
    private JMenuBar menuBar = new JMenuBar();
    private JMenu mFichier = new JMenu("Fichier");
    private JMenu mEdition = new JMenu("Edition");
    private JMenu mAbout = new JMenu("Aide");
    private JMenuItem mNouveau = new JMenuItem("Nouvelle fenêtre");
    private JMenuItem mQuitter = new JMenuItem("Quitter");
    private JMenuItem mNouvelArticle = new JMenuItem("Ajouter un nouvel article");
    private JMenuItem mListArticle = new JMenuItem("Liste des articles");
    private JMenuItem mAide = new JMenuItem("Aide");
    
    
    
        // Utilitaire :
    private String idUniversiteSelected;
    private String nomTypeArticle;
    private boolean typeArticle;
    private ArrayList<String> idAuteurSelected;
    private int indiceShowDetails = 0;
    private String idArticleSelected;
    private int numberWindows = 1;
    
    
    public Fenetre(String title){
// Parametre de la fenetre      --------------------------------------
        this.setTitle(title);
        this.setLocation(850, 150);
	this.setSize(500,610);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        JPanel content = new JPanel();
        JPanel bodyPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        
        // Les bouton
        JButton btnNext = new JButton("Etape suivante");
        
        JPanel pBtnNext = new JPanel(); pBtnNext.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        // ( CardLayout ) Liste des noms de nos conteneurs pour la pile de cartes
        String[] listContent = {"CARD_1 : new article", "CARD_2 : select auteur", "CARD_3", "CARD_4", "CARD_5", "CARD_6"};
        bodyPanel.setLayout(cardLayout);
        bodyPanel.add(articlePanel.getPanelPrincipal(), listContent[0]);
        bodyPanel.add(selectAuteurPanel.getPanelPrincipal(), listContent[1]);
        bodyPanel.add(showArticlePanel.getPanelPrincipal(), listContent[2]);
        bodyPanel.add(auteurPanel.getPanelPrincipal(), listContent[3]);
        bodyPanel.add(selectUniversitePanel.getPanelPrincipal(), listContent[4]);
        bodyPanel.add(showAllArticle.getPanelPrincipal(), listContent[5]);
        
        pBtnNext.add(btnNext);
        content.setLayout(new BorderLayout());
        content.add(bodyPanel, BorderLayout.CENTER);
        content.add(pBtnNext, BorderLayout.SOUTH);
        this.setContentPane(content);
        
        
        
// ------------- MENU -------------
        mFichier.add(mNouveau);
        mFichier.addSeparator();
        mFichier.add(mQuitter);
        mEdition.add(mNouvelArticle);
        mEdition.add(mListArticle);
        mAbout.add(mAide);
        
        // L'implimentation
        menuBar.add(mFichier);
        menuBar.add(mEdition);
        menuBar.add(mAbout);
        this.setJMenuBar(menuBar);
        
        // Les intéractions
        mNouveau.addActionListener((e) -> {
            numberWindows++;
            Fenetre fenetre = new Fenetre("Gestion D'article Fenêtre [" + numberWindows + "]");
            fenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            fenetre.setVisible(true);
        });
        
        mQuitter.addActionListener((e) -> {
            this.dispose();
        });
        
        mNouvelArticle.addActionListener((e) -> {
            articlePanel.refreshPanel();
            cardLayout.show(bodyPanel, listContent[0]);
            btnNext.setText("Etape suivante");
            indice = 1;
        });
        
        mListArticle.addActionListener((e) -> {
            cardLayout.show(bodyPanel,listContent[5]);
            btnNext.setText("Ajouter un nouvel article");
            indice = 4;
        });
        
        mAide.addActionListener((e) -> {
            String textAbout = "Prénom : Yanis\n";
            textAbout = textAbout + "Nom : OULHACI\n";
            textAbout = textAbout + "Groupe : B2-4\n";
            textAbout = textAbout + "E-mail : yanis.indt@gmail.com\n\n";
            textAbout = textAbout + "Etudiant en informatique, passionné par le design, l'informatique et le monde numérique\n\n\n";
            textAbout = textAbout + "Tous cela n'aurait pas était possible sans :\n";
            textAbout = textAbout + "\t*Stack Overflow\n\t-https://stackoverflow.com/\n\n";
            textAbout = textAbout + "\t*Zeste de savoir\n\t-https://zestedesavoir.com/\n\n";
            textAbout = textAbout + "\t*W3Schools\n\t-https://www.w3schools.com/\n\n";
            textAbout = textAbout + "\t*Attacomsian\n\t-https://attacomsian.com\n\n";
            textAbout = textAbout + "\t*Le livre JAVA OpenClassrooms(SDZ)\n\t-https://openclassrooms.com/\n\n";
            textAbout = textAbout + "\t*Google\n\t-https://www.google.com/\n\n";
            ShowDetailsDialog aboutDialog = new ShowDetailsDialog(this, "A propos", textAbout);
            aboutDialog.setVisible(true);
        });
        
// --------------------------------------------------------------
    // Create new auteur
    JButton addAuteur = selectAuteurPanel.getBtnAddNewAuteur();
    addAuteur.addActionListener((ActionEvent e) -> {
        auteurPanel.setEnableJTextField();
        btnNext.setText("Créer l'auteur");
        cardLayout.show(bodyPanel,listContent[3]);
        indice = 5;
        System.out.println(indice);
    });
        
// --------------------------------------------------------------
    // Create new university
    JButton btnUniversiteExiste = auteurPanel.getBtnUniversiteExiste();
    btnUniversiteExiste.addActionListener((e) -> {
        cardLayout.show(bodyPanel,listContent[4]);
        btnNext.setText("Sellectionner l'université");
        indice = 6;
        System.out.println(indice);
    });
// --------------------------------------------------------------
    // Go back auteur existe
    JButton btnAuteurExiste = auteurPanel.getBtnAuteurExiste();
    btnAuteurExiste.addActionListener((e) -> {
        btnNext.setText("Confirmer la sélection");
        indice = 2;
        cardLayout.show(bodyPanel,listContent[1]);
    });
    
// --------------------------------------------------------------
    // Go back university existe
    JButton btnGoCreateUniversity = selectUniversitePanel.getBtnAddNewUniversite();
    btnGoCreateUniversity.addActionListener((e) -> {
        btnNext.setText("Créer l'auteur");
        indice = 5;
        auteurPanel.setEnableJTextField();
        cardLayout.show(bodyPanel,listContent[3]);
    });
    
// --------------------------------------------------------------
    // Go back show details
    JButton btnShowDetails = showArticlePanel.getShowDetails();
    btnShowDetails.addActionListener((e) -> {
            if(typeArticle)
                showDetailsDialog = new ShowDetailsDialog(this, "Detail de l'article C", article, conference, null, idAuteurSelected, typeArticle);
            else
                showDetailsDialog = new ShowDetailsDialog(this, "Detail de l'article R", article, null, revue, idAuteurSelected, typeArticle);
            showDetailsDialog.setVisible(true);
    });
    
// --------------------------------------------------------------
    // Go back show details
    JButton BtnShowArticleSelected = showAllArticle.getBtnShowArticleSelected();
    BtnShowArticleSelected.addActionListener((e) -> {
        try{
            idArticleSelected = showAllArticle.getTableIdSelected();
            auteurPanel.refresh();
            idAuteurSelected = redigerEntity.getAuteurList(idArticleSelected);
            typeArticle= false;
            if(showAllArticle.getTableTypeSelected().equals("Conférence")) {
                article = articleEntity.getArticleById(idArticleSelected, true);
                conference = articleEntity.getConference();
                typeArticle = true;
            } else {
                article = articleEntity.getArticleById(idArticleSelected, false);
                revue = articleEntity.getRevue();
            }
            String aTitle = article.getTitre();
                if(aTitle.length()>45) {
                    aTitle = aTitle.substring(0, 42) + "...";
                }
            showArticlePanel.getTitreArticle().setText(aTitle);
            String contenuArticle = article.cutContenu(article.getContenu());
            showArticlePanel.getContenuArticle().setText(contenuArticle);
            String stringNbPage = String.valueOf("Nombre de page : " + article.getNbPages());
            showArticlePanel.getNbPageArticle().setText(stringNbPage);
            String stringNbMot = Integer.toString(contenuArticle.split("\\s+").length);
            showArticlePanel.getNombreMot().setText("Nombre de mot : " + stringNbMot);

            if(typeArticle)
                showArticlePanel.getTypeArticle().setText("Type : Conférence");
            else
                showArticlePanel.getTypeArticle().setText("Type : Revue");

            showArticlePanel.getNomType().setText("test");
            btnNext.setText("Revenir à la liste des articles");
            cardLayout.show(bodyPanel,listContent[2]);
            indice = 8;
        } catch(Exception exception) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un article!.", "Message d'erreur",
                                JOptionPane.ERROR_MESSAGE);
        }
    });
    
    
/**
 *  Configuration interaction IHM - BDD
 */
        // Configuration du bouton next
        btnNext.addActionListener((ActionEvent e) -> {
            switch(indice) {
                case 1 : {
                    if(articleIsOk()) {
                        if(articlePanel.getTypeArticle() == 0) {
                            typeArticle = true;
                            article = createConference();
                            if(articlePanel.getNomConferene().length() > 22)
                                nomTypeArticle = articlePanel.getNomConferene().substring(0, 20) + "...";
                            else
                                nomTypeArticle = articlePanel.getNomConferene();
                        } else {
                            typeArticle = false;
                            article = createRevue();
                            if(articlePanel.getNomRevue().length() > 22)
                                nomTypeArticle = articlePanel.getNomRevue().substring(0, 20) + "...";
                            else
                                nomTypeArticle = articlePanel.getNomRevue();
                        }
                        if(!article.plagiat(articleEntity.getContenuList())) {
                            
                            selectAuteurPanel.refresh();
                            cardLayout.show(bodyPanel,listContent[1]);
                            btnNext.setText("Confirmer");
                            indice = 2;
                        } else
                            JOptionPane.showMessageDialog(null, "Plagiat detecté, soyez digne!.", "Message d'humiliation",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 2 : {
                    indiceShowDetails = 0;
                    if(selectAuteurPanel.idAuteurIsGood()) {
                        idAuteurSelected = selectAuteurPanel.getIdAuteurSelected();
                        
                        String aTitle = article.getTitre();
                        if(aTitle.length()>45) {
                            aTitle = aTitle.substring(0, 42) + "...";
                        }
                        showArticlePanel.getTitreArticle().setText(aTitle);
                        String contenuArticle = article.cutContenu(article.getContenu());
                        showArticlePanel.getContenuArticle().setText(contenuArticle);
                        String stringNbPage = String.valueOf("Nombre de page : " + article.getNbPages());
                        showArticlePanel.getNbPageArticle().setText(stringNbPage);
                        String stringNbMot = Integer.toString(contenuArticle.split("\\s+").length);
                        showArticlePanel.getNombreMot().setText("Nombre de mot : " + stringNbMot);
                        
                        if(typeArticle)
                            showArticlePanel.getTypeArticle().setText("Type : Conférence");
                        else
                            showArticlePanel.getTypeArticle().setText("Type : Revue");
                        
                        showArticlePanel.getNomType().setText(nomTypeArticle);
                        btnNext.setText("Confirmer maintenant");
                        cardLayout.show(bodyPanel,listContent[2]);
                        indice = 3;
                    }
                    break;
                }
                case 3 : {
                    if(typeArticle) {
                        articleEntity.addArticle(conference);
                        
                        RedigerEntity rediger = new RedigerEntity();
                        rediger.addRedaction(idAuteurSelected, conference);
                    } else {
                        articleEntity.addArticle(revue);
                        RedigerEntity rediger = new RedigerEntity();
                        rediger.addRedaction(idAuteurSelected, revue);
                    }
                    showAllArticle.updateShowAllArticle();
                    
                    // REFRESH ALL :
                    selectAuteurPanel.refresh();
                    selectUniversitePanel.refresh();
                    auteurPanel.refresh();
                    
                    indice = 4;
                    cardLayout.show(bodyPanel,listContent[5]);
                    btnNext.setText("Ajouter un nouvel article");
                    break;
                }
                case 4 : {
                    btnNext.setText("Etape suivante");
                    articlePanel.refreshPanel();
                    cardLayout.show(bodyPanel, listContent[0]);
                    indice = 1;
                    break;
                }
                case 5 : {
                    if(auteurPanel.newAuteurIsGood() && auteurPanel.newUniversiteIsGood()) {
                        Auteur auteurTmp = new Auteur(auteurPanel.getNomAuteur(), auteurPanel.getPrenomAuteur(), auteurPanel.getEmailAuteur());
                        Universite universiteTmp = new Universite(auteurPanel.getNomUniversite(), auteurPanel.getSiteUniversite(), auteurPanel.getAdressUniversite());
                        universiteEntity.addUniversite(universiteTmp);
                        int getIdUniversite = universiteEntity.getIdUniversite(universiteTmp);
                        auteurEntity.addAuteur(auteurTmp, getIdUniversite);
                        selectAuteurPanel.updateModel();
                        cardLayout.show(bodyPanel,listContent[1]);
                        indice = 2;
                        btnNext.setText("Confirmer");
                    }
                    break;
                }
                case 6 : {
                    System.out.println("cas 6");
                    if(selectUniversitePanel.idUniversiteIsGood()) {
                        idUniversiteSelected = selectUniversitePanel.getIdUniversiteSelected();
                        btnNext.setText("Confirmer");
                        auteurPanel.setDisableJTextField(selectUniversitePanel.getValueName(), selectUniversitePanel.getValueWebSite(), selectUniversitePanel.getValueAdresse());
                        cardLayout.show(bodyPanel,listContent[3]);
                        indice = 7;
                    }
                    break;
                }
                case 7 : {
                    if(auteurPanel.newAuteurIsGood() && !idUniversiteSelected.equals("-1")) {
                        Auteur auteurTmp = new Auteur(auteurPanel.getNomAuteur(), auteurPanel.getPrenomAuteur(), auteurPanel.getEmailAuteur());
                        int getIdUniversite = Integer.parseInt(idUniversiteSelected);
                        auteurEntity.addAuteur(auteurTmp, getIdUniversite);
                        selectAuteurPanel.updateModel();
                        cardLayout.show(bodyPanel,listContent[1]);
                        btnNext.setText("Etape suivante");
                        indice = 2;
                    }
                    break;
                }
                
                case 8 : {
                    btnNext.setText("Ajouter un article");
                    cardLayout.show(bodyPanel,listContent[5]);
                    indice = 4;
                }
                default:
                    break;
            }
        });
    }
    
    private boolean articleIsOk() {
        boolean articleIsOk = true;
        if(articlePanel.getTitreArticle().length() == 0) {
            articleIsOk = false;
            JOptionPane.showMessageDialog(null, "Le titre de l'article ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(articlePanel.getContenuArticle().length() == 0) {
            articleIsOk = false;
            JOptionPane.showMessageDialog(null, "Le contenu de l'article ne peut être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(articlePanel.getNbPage() == -1 || articlePanel.getNbPage() > 32) {
            articleIsOk = false;
            JOptionPane.showMessageDialog(null, "Le nombre de page n'est pas valide", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else if(articlePanel.getTypeArticle() == 0) {
            if(articlePanel.getNomConferene().length() == 0) {
                articleIsOk = false;
                JOptionPane.showMessageDialog(null, "Le nom de la conférence ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
            } else if(articlePanel.getLieuConference().length() == 0) {
                articleIsOk = false;
                JOptionPane.showMessageDialog(null, "Le lieu de la conférence ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        } else if(articlePanel.getTypeArticle() == 1) {
            if(articlePanel.getNomRevue().length() == 0) {
                articleIsOk = false;
                JOptionPane.showMessageDialog(null, "Le nom de la revue ne peut pas être vide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
            } else if(articlePanel.getFacteurImpact() == -1 || articlePanel.getFacteurImpact() > 5 || articlePanel.getFacteurImpact() < 0) {
                articleIsOk = false;
                JOptionPane.showMessageDialog(null, "Le facteur d'impact n'est pas valide.", "Message d'erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        return articleIsOk;
    }

    private Article createRevue() {
        Article revueTmp;
        this.revue = new Revue(articlePanel.getTitreArticle(), articlePanel.getContenuArticle(), articlePanel.getNbPage(), articlePanel.getNomRevue(), articlePanel.getFacteurImpact());
        revueTmp = this.revue;
        return revueTmp;
    }
    private Article createConference() {
        Article conferenceTmp;
        this.conference = new Conference(articlePanel.getTitreArticle(), articlePanel.getContenuArticle(), articlePanel.getNbPage(), articlePanel.getNomConferene(), articlePanel.getLieuConference());
        conferenceTmp = this.conference;
        return conferenceTmp;
    }
}