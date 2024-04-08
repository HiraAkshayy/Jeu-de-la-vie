package Jeu;

public class CommandeNext extends CommandeJeu{
    
    public CommandeNext(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    public void executer(){
        jeu.calculerGenerationSuivante();
    }
}
