package effets;

import consommables.Consommable;
import personnages.Personnage;

public class EffetConsommableDegats implements EffetConsommable{
    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Consommable consommable) {
        // TODO : ajouter un type physique ou magique dans le consommable
        int puissanceTotale = consommable.puissance() + lanceur.getStatsEffectives().puissanceMagique();
        int degats = puissanceTotale - cible.getStatsEffectives().resistanceMagique();
        boolean degatsInfliges = cible.subirDegats(degats);
        if (degatsInfliges) {
            IO.println(String.format("%s a infligé %s dégât(s) à %s avec : %s!", lanceur.getNom(), degats, cible.getNom(), consommable.nom()));
        } else {
            IO.println(String.format("%s de %s est inéfficace.", consommable.nom(), lanceur.getNom()));
        }
    }
}
