package Jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Cell.*;
import UI.*;
import Regles.*;

public class JeuDeLaVie implements Observable{
    private Cellule[][] grille;
    private int xMax, yMax;
    private Visiteur visiteur;

    private List<Observateur> observateurs;
    private List<Commande> commandes;

    public JeuDeLaVie(int x, int y){
        xMax = x;
        yMax = y;
        grille = new Cellule[xMax][yMax];
        
        observateurs = new ArrayList<>();
        commandes = new ArrayList<>();

        initialiseGrille();
    }

    /**
     * Méthode qui renvoie le xMax du JeuDeLaVie
     * @return xMax
     */
    public int getXMax(){
        return xMax;
    }

    /**
     * Méthode qui renvoie le yMax du JeuDeLaVie
     * @return yMax
     */
    public int getYMax(){
        return yMax;
    }

    /**
     * Méthode qui initialise la grille du JeuDeLaVie avec des cellules
     * vivantes ou mortes (la sélection de l'état des cellules est aléatoire)
     */
    public void initialiseGrille(){
        for(int x = 0; x < xMax; x++){
            for(int y = 0; y < yMax; y++){
                if(Math.random() < 0.5){
                    grille[x][y] = new Cellule(x, y, CelluleEtatVivant.getEtat());
                }
                else{
                    grille[x][y] = new Cellule(x, y, CelluleEtatMort.getEtat());
                }
            }
        }
    }

    /**
     * Méthode qui va retourner la Cellule aux coordonnées x et y
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public Cellule getGrilleXY(int x, int y){
        if((x >= 0 && x < xMax) && (y >= 0 && y < yMax)){
            return grille[x][y];
        }
        else{
            return new Cellule(x, y, CelluleEtatMort.getEtat());
        }
    }

    /**
     * Méthode qui va attacher un nouvel observateur au JeuDeLaVie
     */
    public void attacheObservateur(Observateur o){
        observateurs.add(o);
    }

    /**
     * Méthode qui va détacher un observateur du JeuDeLaVie
     */
    public void detacheObservateur(Observateur o){
        observateurs.remove(o);
    }

    /**
     * Méthode qui va notifier tous les observateurs actuellement
     * attachés pour qu'ils s'actualisent
     */
    public void notifieObservateurs(){
        for(Observateur o: observateurs){
            o.actualise();
        }
    }

    /**
     * Méthode qui ajoute une commande à la liste d'attente des commandes
     * @param c Commande à ajouter
     */
    public void ajouteCommande(Commande c){
        commandes.add(c);
    }

    /**
     * Méthode qui exécute toutes les commandes de la liste
     * puis vide la liste
     */
    public void executeCommandes(){
        for(Commande c: commandes){
            c.executer();
        }

        commandes.clear();
    }

    /**
     * Méthode pour changer le visiteur actuel
     * @param v Nouveau visiteur
     */
    public void setVisiteur(Visiteur v){
        this.visiteur = v;
    }

    /**
     * Méthode pour envoyer le visiteur à toutes les cellules 
     * du jeu
     */
    public void distribueVisiteur(){
        for(int x = 0; x < xMax; x++){
            for(int y = 0; y < yMax; y++){
                getGrilleXY(x, y).accepte(visiteur);
            }
        }
    }

    /**
     * Méthode pour calculer la prochaine génération du 
     * jeu
     */
    public void calculerGenerationSuivante(){
        distribueVisiteur();
        executeCommandes();
        notifieObservateurs();
    }

    public static void main(String args[]){
        JeuDeLaVie jeu = new JeuDeLaVie(150, 150);
        Visiteur visiteur = new VisiteurClassique(jeu);
        jeu.setVisiteur(visiteur);

        JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
        jeu.attacheObservateur(ui);

        while(true){
            jeu.calculerGenerationSuivante();
            try{
                TimeUnit.MILLISECONDS.sleep(250);
            }catch(InterruptedException e){}
        }
    }
}
