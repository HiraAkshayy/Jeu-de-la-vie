package Regles;

import Cell.*;
import Jeu.JeuDeLaVie;

public abstract class Visiteur {
    protected JeuDeLaVie jeu;

    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    public void visiteCelluleVivante(Cellule cellule){}

    public void visiteCelluleMorte(Cellule cellule){}
}
