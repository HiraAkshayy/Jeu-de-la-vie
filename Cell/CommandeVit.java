package Cell;

public class CommandeVit extends Commande{
    
    public CommandeVit(Cellule c){
        cellule = c;
    }

    public void executer(){
        cellule.vit();
    }
}
