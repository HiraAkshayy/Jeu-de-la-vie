package Regles;

import Cell.*;
import Jeu.JeuDeLaVie;

public class VisiteurClassique extends Visiteur{
    private static VisiteurClassique visiteurClassique = null;
    
    private VisiteurClassique(JeuDeLaVie jeu){
        super(jeu);
    }

    public static VisiteurClassique getInstance(JeuDeLaVie jeu){
        if(visiteurClassique == null)
            visiteurClassique = new VisiteurClassique(jeu);
        return visiteurClassique;
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
