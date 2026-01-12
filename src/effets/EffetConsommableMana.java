package effets;

import consommables.Consommable;
import personnages.Personnage;

public class EffetConsommableMana implements EffetConsommable {

    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Consommable consommable) {
        int manaRecupere = cible.recupererMana(consommable.puissance());
        if (manaRecupere > 0) {
            IO.println(String.format("%s a rendu %s point(s) de mana à %s avec : %s!", lanceur.getNom(), manaRecupere, cible.getNom(), consommable.nom()));
        } else {
            IO.println(String.format("%s de %s n'a eu aucun effet.", consommable.nom(), lanceur.getNom()));
        }
    }
}
