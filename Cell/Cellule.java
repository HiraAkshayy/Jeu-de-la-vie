package Cell;

import Jeu.*;
import Regles.*;

/**
 * Classe qui représente une cellule du jeu de la vie.
 * Une cellule peut mourir ou naître, et on connaît son état actuel
 */
public class Cellule {
    private CelluleEtat etat;
    private int x, y;

    public Cellule(int x, int y, CelluleEtat etat){
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * Méthode qui fait vivre la cellule
     */
    public void vit(){
        etat = etat.vit();
    }

    /**
     * Méthode qui fait mourir la cellule
     */
    public void meurt(){
        etat = etat.meurt();
    }

    /**
     * Méthode qui indique si la cellule est vivante ou non
     * @return Un boolean indiquant si la cellule est vivante
     */
    public boolean estVivante(){
        return etat.estVivante();
    }

    /**
     * Méthode qui affiche le nombre de voisins vivants autour de la cellule
     * @param jeu JeuDeLaVie dans lequel est la cellule
     * @return Le nombre de voisins vivants autour de la cellule
     */
    public int nombreVoisinesVivantes(JeuDeLaVie jeu){
        int total = 0;

        total += jeu.getGrilleXY(x - 1, y - 1).estVivante() ? 1 : 0;
        total += jeu.getGrilleXY(x - 1, y).estVivante() ? 1 : 0;
        total += jeu.getGrilleXY(x - 1, y + 1).estVivante() ? 1 : 0;

        total += jeu.getGrilleXY(x, y - 1).estVivante() ? 1 : 0;
        total += jeu.getGrilleXY(x, y + 1).estVivante() ? 1 : 0;

        total += jeu.getGrilleXY(x + 1, y - 1).estVivante() ? 1 : 0;
        total += jeu.getGrilleXY(x + 1, y).estVivante() ? 1 : 0;
        total += jeu.getGrilleXY(x + 1, y + 1).estVivante() ? 1 : 0;

        return total;
    }

    public void accepte(Visiteur visiteur){
        etat.accepte(visiteur, this);
    }
}
