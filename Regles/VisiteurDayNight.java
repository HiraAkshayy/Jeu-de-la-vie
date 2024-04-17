package Regles;

import Jeu.JeuDeLaVie;
import Cell.*;

public class VisiteurDayNight extends Visiteur{
    public static VisiteurDayNight visiteurDayNight = null;
    
    private VisiteurDayNight(JeuDeLaVie jeu){
        super(jeu);
    }

    public static VisiteurDayNight getInstance(JeuDeLaVie jeu){
        if(visiteurDayNight == null)
            visiteurDayNight = new VisiteurDayNight(jeu);
        return visiteurDayNight;
    }

    public void visiteCelluleVivante(Cellule cellule){
        int nbVivantes = cellule.nombreVoisinesVivantes(jeu);
        if(nbVivantes == 5 || nbVivantes < 3){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    public void visiteCelluleMorte(Cellule cellule){
        int nbVivantes = cellule.nombreVoisinesVivantes(jeu);
        if(nbVivantes == 3 || nbVivantes == 6 || nbVivantes == 7 || nbVivantes == 8){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
