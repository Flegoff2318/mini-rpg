package effets;

import personnages.Personnage;
import sorts.Sort;

public interface EffetSort {
    void appliquerEffet(Personnage lanceur, Personnage cible, Sort sort);
}
