package Regles;

import Cell.*;
import Jeu.JeuDeLaVie;

public class VisiteurClassique extends Visiteur{
    
    public VisiteurClassique(JeuDeLaVie jeu){
        super(jeu);
    }

    public void visiteCelluleVivante(Cellule cellule){
        if(cellule.nombreVoisinesVivantes(jeu) < 2 || cellule.nombreVoisinesVivantes(jeu) > 3){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    public void visiteCelluleMorte(Cellule cellule){
        if(cellule.nombreVoisinesVivantes(jeu) == 3){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
