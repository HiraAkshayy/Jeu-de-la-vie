package Cell;

import Regles.*;

public class CelluleEtatVivant implements CelluleEtat{
    private static CelluleEtatVivant celluleEtatVivant = null;

    /**
     * Constructeur mit en private pour appliquer le
     * design pattern singleton
     */
    private CelluleEtatVivant(){}

    /**
     * Méthode de classe utilisée pour la création d'un
     * état CelluleEtatVivant
     */
    public static CelluleEtatVivant getEtat(){
        if(CelluleEtatVivant.celluleEtatVivant == null){
            CelluleEtatVivant.celluleEtatVivant = new CelluleEtatVivant();
        }
        return CelluleEtatVivant.celluleEtatVivant;
    }

    public CelluleEtat vit(){
        return this;
    }

    public CelluleEtat meurt(){
        return CelluleEtatMort.getEtat();
    }

    public boolean estVivante(){
        return true;
    }

    public void accepte(Visiteur visiteur, Cellule cellule){
        visiteur.visiteCelluleVivante(cellule);
    }
}
