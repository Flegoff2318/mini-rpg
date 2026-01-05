package effets;

import personnages.Personnage;
import sorts.Sort;

public class EffetSortDegats implements EffetSort {
    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Sort sort) {
        int degats = sort.puissance()-cible.getResistanceMagique();
        boolean degatsInfliges = cible.subirDegats(degats);
        if(degatsInfliges){
            IO.println(String.format("%s a infligé %s dégât(s) à %s avec le sort : %s!",lanceur.getNom(),degats,cible.getNom(),sort.nom()));
        }else{
            IO.println(String.format("Le sort %s de %s est inéfficace.",sort.nom(),lanceur.getNom()));
        }
    }
}
