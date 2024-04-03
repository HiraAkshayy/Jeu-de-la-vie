package Cell;

import Regles.*;

public class CelluleEtatMort implements CelluleEtat{
    private static CelluleEtatMort celluleEtatMort = null;

    /**
     * Constructeur mit en private pour appliquer le
     * design pattern singleton
     */
    private CelluleEtatMort(){}

    /**
     * Méthode de classe utilisée pour la création d'un
     * état CelluleEtatMort
     */
    public static CelluleEtatMort getEtat(){
        if(CelluleEtatMort.celluleEtatMort == null){
            CelluleEtatMort.celluleEtatMort = new CelluleEtatMort();
        }
        return CelluleEtatMort.celluleEtatMort;
    }

    public CelluleEtat vit(){
        return CelluleEtatVivant.getEtat();
    }

    public CelluleEtat meurt(){
        return this;
    }

    public boolean estVivante(){
        return false;
    }

    public void accepte(Visiteur visiteur, Cellule cellule){
        visiteur.visiteCelluleMorte(cellule);
    }
}
