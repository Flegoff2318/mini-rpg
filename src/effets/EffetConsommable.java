package effets;

import consommables.Consommable;
import personnages.Personnage;

public interface EffetConsommable {
    void appliquerEffet(Personnage lanceur, Personnage cible, Consommable consommable);
}
