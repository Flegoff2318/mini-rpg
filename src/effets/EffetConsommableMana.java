package effets;

import consommables.Potion;
import personnages.Personnage;

public class EffetConsommableMana implements EffetConsommable {

    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Potion potion) {
        int manaRecupere = cible.recupererMana(potion.puissance());
        if (manaRecupere > 0) {
            IO.println(String.format("%s a rendu %s point(s) de mana à %s avec : %s!", lanceur.getNom(), manaRecupere, cible.getNom(), potion.nom()));
        } else {
            IO.println(String.format("%s de %s n'a eu aucun effet.", potion.nom(), lanceur.getNom()));
        }
    }
}
