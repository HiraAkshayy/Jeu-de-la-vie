package Jeu;

public class CommandeMarche extends CommandeJeu{
    
    public CommandeMarche(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    public void executer(){
        jeu.marcheArret();
    }
}
