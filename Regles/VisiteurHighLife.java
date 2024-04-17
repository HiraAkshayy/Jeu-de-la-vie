package Regles;

import Cell.*;
import Jeu.JeuDeLaVie;

public class VisiteurHighLife extends Visiteur{
    public static VisiteurHighLife visiteurHighLife = null;
    
    public VisiteurHighLife(JeuDeLaVie jeu){
        super(jeu);
    }

    public static VisiteurHighLife getInstance(JeuDeLaVie jeu){
        if(visiteurHighLife == null)
            visiteurHighLife = new VisiteurHighLife(jeu);
        return visiteurHighLife;
    }

    public void visiteCelluleVivante(Cellule cellule){
        if(cellule.nombreVoisinesVivantes(jeu) < 2 || cellule.nombreVoisinesVivantes(jeu) > 3){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    public void visiteCelluleMorte(Cellule cellule){
        if(cellule.nombreVoisinesVivantes(jeu) == 3 || cellule.nombreVoisinesVivantes(jeu) == 6){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
