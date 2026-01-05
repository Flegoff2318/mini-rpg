package effets;

import personnages.Personnage;
import sorts.Sort;

public class EffetSortSoins implements EffetSort {
    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Sort sort) {
        int soins = cible.soigner(sort.puissance());
        if(soins>0){
            IO.println(String.format("%s a rendu %s point(s) de vie à %s avec le sort : %s!",lanceur.getNom(),soins,cible.getNom(),sort.nom()));
        }else{
            IO.println(String.format("Le sort %s de %s est inéfficace.",sort.nom(),lanceur.getNom()));
        }
    }
}
