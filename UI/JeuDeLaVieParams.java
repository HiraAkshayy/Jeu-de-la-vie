package UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Jeu.*;

/**
 * Classe qui représente l'interface des paramètres du jeu
 */
public class JeuDeLaVieParams extends JFrame{
    private final int VIT_MIN = 100;
    private final int VIT_MAX = 1000;
    private final int VIT_INIT = 500;

    private JeuDeLaVie jeu;
    private boolean on;
    private int delai;

    private JButton boutonOn;
    private JButton boutonNextGen;
    private JSlider vitSlider;
    private JPanel panel;

    public JeuDeLaVieParams(JeuDeLaVie jeu){
        super("Jeu de la Vie");
        this.jeu = jeu;
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Initialisation des boutons des paramètres
         */
        boutonOn = new JButton("Marche/Arrêt");
        boutonNextGen = new JButton("Next");
        vitSlider = new JSlider(VIT_MIN, VIT_MAX, VIT_INIT);

        /*
         * Ajout d'une action pour chaque bouton et le slider
         */
        boutonOn.addActionListener((e) -> butOn());

        boutonNextGen.addActionListener((e) -> butNext());

        vitSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                jeu.setDelai((int)source.getValue());
            }
        });

        /*
         * Ajout des boutons et du slider sur l'interface
         */
        panel = new JPanel();

        panel.add(vitSlider);
        panel.add(boutonOn);
        panel.add(boutonNextGen);

        this.add(panel);
        this.setSize(600, 250);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

        on = true;
        delai = VIT_INIT;
    }

    /**
     * Méthode qui change l'état en marche du jeu
     */
    private void butOn(){
        on = on ? false : true;
        jeu.ajouteCommandeJeu(new CommandeMarche(jeu));
    }

    /**
     * Méthode qui appelle le calcul de la génération suivante si le jeu 
     * est arrêté
     */
    private void butNext(){
        if(!on){
            jeu.ajouteCommandeJeu(new CommandeNext(jeu));
        }
    }

    public boolean getEtatOn(){
        return on;
    }

    public int getDelai(){
        return delai;
    }
}
