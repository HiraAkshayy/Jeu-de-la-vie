package UI;

import Jeu.JeuDeLaVie;

/**
 * Classe qui représente les statistiques actuelles du jeu
 * (à quelle génération on est et combien de cellules sont vivantes pendant
 * cette génération)
 */
public class JeuDeLaVieStats implements Observateur{
    private JeuDeLaVie jeu;
    private int genCourante;

    public JeuDeLaVieStats(JeuDeLaVie jeu){
        this.jeu = jeu;
        this.genCourante = 0;
    }

    public void actualise(){
        System.out.println("La génération courante est "+ (++genCourante));
        System.out.println("Il y a "+ nombreCellulesVivantes() +" cellules vivantes actuellement\n");
    }

    private int nombreCellulesVivantes(){
        int total = 0;

        for(int x = 0; x < jeu.getXMax(); x++){
            for(int y = 0; y < jeu.getYMax(); y++){
                total += jeu.getGrilleXY(x, y).estVivante() ? 1 : 0;
            }
        }

        return total;
    }
}
