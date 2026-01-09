package equipements;

import core.Statistiques;

public record Armure(String nom, EmplacementArmure emplacementArmure, Statistiques statistiques, int niveauRequis, int prixAchat, int prixVente) implements Equipement{
    @Override
    public String toString() {
        return String.format("%s - Emplacement : %s, Statistiques : [Vie : %s][Mana : %s][Puissance d'attaque : %s][Puissance Magique : %s][Armure : %s][Resistance Magique : %s][Vitesse : %s]",
                nom(),
                emplacementArmure().label,
                statistiques().pointsVieMax(),
                statistiques().manaMax(),
                statistiques().attaquePhysique(),
                statistiques().puissanceMagique(),
                statistiques().armure(),
                statistiques().resistanceMagique(),
                statistiques().vitesse()
        );
    }
}
