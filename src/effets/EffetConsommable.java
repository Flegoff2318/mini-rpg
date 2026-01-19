package effets;

import consommables.Potion;
import personnages.Personnage;

public interface EffetConsommable {
    void appliquerEffet(Personnage lanceur, Personnage cible, Potion potion);
}
