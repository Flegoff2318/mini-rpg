package effets;

import consommables.Potion;
import personnages.Personnage;

public class EffetConsommableSoins implements EffetConsommable {

    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Potion potion) {
        int soins = cible.soigner(potion.puissance());
        if (soins > 0) {
            IO.println(String.format("%s a rendu %s point(s) de vie à %s avec : %s!", lanceur.getNom(), soins, cible.getNom(), potion.nom()));
        } else {
            IO.println(String.format("%s de %s n'a eu aucun effet.", potion.nom(), lanceur.getNom()));
        }
    }
}
