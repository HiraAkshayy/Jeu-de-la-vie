import java.util.concurrent.TimeUnit;

import Jeu.*;
import Regles.*;

public class Main {
    public static void main(String args[]){
        JeuDeLaVie jeu = new JeuDeLaVie(150, 150, 0.5, true, true, true);
        Visiteur visiteur = VisiteurClassique.getInstance(jeu);
        jeu.setVisiteur(visiteur);

        jeu.notifieObservateurs();

        while(true){
            if(jeu.isOn()){
                jeu.calculerGenerationSuivante();
                try{
                    TimeUnit.MILLISECONDS.sleep(jeu.delai);
                }catch(InterruptedException e){}
            }
            try{
                TimeUnit.MILLISECONDS.sleep(50);
            }catch(InterruptedException e){}
        }
    }
}
