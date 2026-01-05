package consommables;

import personnages.Personnage;

public class ContexteConsommable {
    public boolean utiliserConsommable(Personnage lanceur, Personnage cible, Consommable consommable) {
        boolean consommableUtilise = lanceur.getInventaire().supprimerConsommable(consommable);
        if (consommableUtilise) {
            consommable.effetConsommable().appliquerEffet(lanceur, cible, consommable);
            return true;
        }
        return false;
    }
}
