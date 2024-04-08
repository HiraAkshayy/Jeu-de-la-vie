package UI;

import Jeu.*;
import java.awt.*;
import javax.swing.*;

/**
 * Classe qui représente l'interface graphique du jeu (affichage des cellules 
 * au fil des générations)
 */
public class JeuDeLaVieUI extends JFrame implements Observateur{
    private JeuDeLaVie jeu;

    public JeuDeLaVieUI(JeuDeLaVie jeu){
        super("Jeu de la Vie");
        this.jeu = jeu;
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(jeu.getXMax()*3, jeu.getYMax()*3);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Méthode pour actualiser le JeuDeLaVie
     */
    public void actualise(){
        repaint();
    }

    /**
     * Méthode pour afficher le JeuDeLaVie
     */
    public void paint(Graphics g){
        super.paint(g);
        for(int x = 0; x < jeu.getXMax(); x++){
            for(int y = 0; y < jeu.getYMax(); y++){
                if(jeu.getGrilleXY(x, y).estVivante()){
                    g.fillOval(x * 3, y * 3, 3, 3);
                }
            }
        }
    }
}
