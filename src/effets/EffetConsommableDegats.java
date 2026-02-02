package effets;

import consommables.Potion;
import personnages.Personnage;

public class EffetConsommableDegats implements EffetConsommable{
    @Override
    public void appliquerEffet(Personnage lanceur, Personnage cible, Potion potion) {
        int puissanceTotale = potion.puissance() + lanceur.getStatsEffectives().puissanceMagique();
        int degats = puissanceTotale - cible.getStatsEffectives().resistanceMagique();
        boolean degatsInfliges = cible.subirDegats(degats);
        if (degatsInfliges) {
            IO.println(String.format("%s a infligé %s dégât(s) à %s avec : %s!", lanceur.getNom(), degats, cible.getNom(), potion.nom()));
        } else {
            IO.println(String.format("%s de %s est inéfficace.", potion.nom(), lanceur.getNom()));
        }
    }
}
