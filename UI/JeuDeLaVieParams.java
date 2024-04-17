package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;
import java.util.ArrayList;

import Jeu.*;
import Regles.*;

/**
 * Classe qui représente l'interface des paramètres du jeu
 */
public class JeuDeLaVieParams extends JFrame implements Observable{
    private final int VIT_MIN = 100;
    private final int VIT_MAX = 1000;
    private final int VIT_INIT = 450;

    private List<Observateur> observateurs;
    private JeuDeLaVie jeu;
    private Visiteur regle;
    private int delai;

    private boolean on;
    private boolean marche;
    private boolean next;
    private boolean restart;

    private JButton boutonOn;
    private JButton boutonNextGen;
    private JButton boutonRestart;

    private JSlider vitSlider;

    private JMenuBar bar;
    private JMenu regles;
    private JLabel regleActuelle;

    private JPanel panelParams;
    private JPanel panelInfos;

    public JeuDeLaVieParams(JeuDeLaVie j){
        super("Jeu de la Vie");
        this.jeu = j;
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        observateurs = new ArrayList<>();

        /*
         * Initialisation des boutons des paramètres
         */
        boutonOn = new JButton("Marche/Arrêt");
        boutonNextGen = new JButton("Next");
        boutonRestart = new JButton("Recommencer");

        vitSlider = new JSlider(VIT_MIN, VIT_MAX, VIT_INIT);
        vitSlider.setPaintLabels(true);
        vitSlider.setPaintTicks(true);

        bar = new JMenuBar();
        regles = new JMenu("Règles");
        regle = VisiteurClassique.getInstance(jeu);
        regleActuelle = new JLabel("La règle actuelle est : Classique");

        /*
         * Ajout d'une action pour chaque bouton et le slider
         */
        boutonOn.addActionListener((e) -> butOn());

        boutonNextGen.addActionListener((e) -> butNext());

        boutonRestart.addActionListener((e) -> butRestart());

        vitSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                delai = VIT_MAX - (int)source.getValue();
                notifieObservateurs();
            }
        });

        /*
         * Ajout d'actions sur les éléments du menu déroulant
         */
        Action classique = new AbstractAction("Classique"){
            @Override
            public void actionPerformed(ActionEvent e){
                regle = VisiteurClassique.getInstance(jeu);
                regleActuelle.setText("La règle actuelle est : Classique");
            }
        };

        Action highlife = new AbstractAction("High Life"){
            @Override
            public void actionPerformed(ActionEvent e){
                regle = VisiteurHighLife.getInstance(jeu);
                regleActuelle.setText("La règle actuelle est : High Life");

            }
        };

        Action daynight = new AbstractAction("Day & Night"){
            @Override
            public void actionPerformed(ActionEvent e){
                regle = VisiteurDayNight.getInstance(jeu);
                regleActuelle.setText("La règle actuelle est : Day & Night");
                
            }
        };

        regles.add(classique);
        regles.add(highlife);
        regles.add(daynight);

        /*
         * Ajout des boutons, du slider et du menu sur l'interface
         */
        bar.add(regles);
        panelParams = new JPanel();

        panelParams.add(vitSlider);
        panelParams.add(boutonOn);
        panelParams.add(boutonNextGen);
        panelParams.add(boutonRestart);
        panelParams.add(bar);

        panelInfos = new JPanel(new GridLayout(2, 1));
        
        panelInfos.add(panelParams);
        panelInfos.add(regleActuelle);

        this.add(panelInfos);
        this.setSize(600, 250);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        on = false;
        delai = VIT_INIT;
    }

    /**
     * Méthode qui va demander au jeu de s'arrêter ou de reprendre
     */
    private void butOn(){
        on = on ? false : true;
        marche = true;
        if(on)
            boutonNextGen.setEnabled(false);
        else
            boutonNextGen.setEnabled(true);
        notifieObservateurs();
        marche = false;
    }

    /**
     * Méthode qui va demander au jeu de passer à la génération suivante
     */
    private void butNext(){
        next = true;
        notifieObservateurs();
        next = false;
    }

    /**
     * Méthode qui va demander au jeu de réinitialiser la grille
     */
    private void butRestart(){
        restart = true;
        notifieObservateurs();
        restart = false;
    }

    public boolean getEtatMarche(){
        return marche;
    }

    public boolean getEtatNext(){
        return next;
    }

    public boolean getEtatRestart(){
        return restart;
    }

    public int getDelai(){
        return delai;
    }

    public Visiteur getRegle(){
        return regle;
    }

    public void attacheObservateur(Observateur o){
        observateurs.add(o);
    }

    public void detacheObservateur(Observateur o){
        observateurs.remove(o);
    }

    public void notifieObservateurs(){
        for(Observateur o: observateurs){
            o.actualise();
        }
    }
}
