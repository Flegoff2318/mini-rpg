package consommables;

import personnages.Personnage;

public class ContexteConsommable {
    public boolean utiliserConsommable(Personnage lanceur, Personnage cible, Consommable consommable) {
        boolean consommableUtilise = lanceur.getInventaire().retirerConsommables(consommable,1);
        if (consommableUtilise) {
            consommable.effetConsommable().appliquerEffet(lanceur, cible, consommable);
            return true;
        }
        return false;
    }
}
