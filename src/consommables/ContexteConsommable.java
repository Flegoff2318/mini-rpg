package consommables;

import personnages.Personnage;

public class ContexteConsommable {
    public boolean utiliserConsommable(Personnage lanceur, Personnage cible, Potion potion) {
        boolean consommableUtilise = lanceur.getInventaire().retirerConsommables(potion,1);
        if (consommableUtilise) {
            potion.effetConsommable().appliquerEffet(lanceur, cible, potion);
            return true;
        }
        return false;
    }
}
