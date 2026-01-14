package effets;

import personnages.Personnage;
import sorts.Sort;

public class EffetSortDegats implements EffetSort {
    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Sort sort) {
        int puissanceTotale;
        boolean physique = false;
        switch (sort.archetype()) {
            case ASSASSIN, GUERRIER -> {
                puissanceTotale = sort.puissance() + lanceur.getStatsEffectives().attaquePhysique();
                physique = true;
            }
            default -> puissanceTotale = sort.puissance() + lanceur.getStatsEffectives().puissanceMagique();
        }
        int degats = puissanceTotale - cible.getStatsEffectives().resistanceMagique();
        boolean degatsInfliges = cible.subirDegats(degats);
        String typeSort = physique ? "la technique" : "le sort";
        if (degatsInfliges) {
            IO.println(String.format("%s a infligé %s dégât(s) à %s avec %s : %s!", lanceur.getNom(), degats, cible.getNom(), typeSort, sort.nom()));
        } else {
            IO.println(String.format("%s %s de %s est inéfficace.", typeSort, sort.nom(), lanceur.getNom()));
        }
    }
}
