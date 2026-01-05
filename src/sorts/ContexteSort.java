package sorts;

import personnages.Personnage;

public class ContexteSort {
    // retour d'un booléen pour vérifier plus tard si le sort à bien été lancé
    public boolean lancerSort(Personnage lanceur, Personnage cible, Sort sort) {
        if(lanceur.getMana()<sort.coutMana()){
            IO.println("Pas assez de mana.");
            return false;
        }else{
            lanceur.consommerMana(sort.coutMana());
            sort.effetSort().appliquerEffet(lanceur,cible,sort);
            return true;
        }
    }
}
