package sorts;

import personnages.Personnage;

public interface EffetSort {
    void appliquerEffet(Personnage lanceur, Personnage cible, Sort sort);
}
