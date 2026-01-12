package effets;

import personnages.Personnage;
import sorts.Sort;

public class EffetSortSoins implements EffetSort {
    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Sort sort) {
        int puissanceTotale = sort.puissance() + lanceur.getStatsEffectives().puissanceMagique();
        int soins = cible.soigner(puissanceTotale);
        if (soins > 0) {
            IO.println(String.format("%s a rendu %s point(s) de vie à %s avec le sort : %s! Points de vie actuels : %d", lanceur.getNom(), soins, cible.getNom(), sort.nom(), cible.getPointsVie()));
        } else {
            IO.println(String.format("Le sort %s de %s est inéfficace.", sort.nom(), lanceur.getNom()));
        }
    }
}
