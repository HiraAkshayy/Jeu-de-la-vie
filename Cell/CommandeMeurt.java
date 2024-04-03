package Cell;

public class CommandeMeurt extends Commande{
    
    public CommandeMeurt(Cellule c){
        cellule = c;
    }

    public void executer(){
        cellule.meurt();
    }
}
