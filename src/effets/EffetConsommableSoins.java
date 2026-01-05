package effets;

import consommables.Consommable;
import personnages.Personnage;

public class EffetConsommableSoins implements EffetConsommable {

    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Consommable consommable) {
        int soins = cible.soigner(consommable.puissance());
        if (soins > 0) {
            IO.println(String.format("%s a rendu %s point(s) de vie à %s avec : %s!", lanceur.getNom(), soins, cible.getNom(), consommable.nom()));
        } else {
            IO.println(String.format("%s de %s n'a eu aucun effet.", consommable.nom(), lanceur.getNom()));
        }
    }
}
