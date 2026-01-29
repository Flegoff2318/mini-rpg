package equipements;

import core.Statistiques;

public record Armure(Armurerie type, String nom, EmplacementArmure emplacementArmure, Statistiques statistiques,
                     int niveauRequis, int prixAchat, int prixVente) implements Equipement {
    @Override
    public String toString() {
        return String.format("%s - SLOT : %s - LEVEL : [%s] : HP:%s, MANA:%s, ATK:%s, MAG:%s, ARMOR:%s, MR:%s, VIT:%s",
                nom(),
                emplacementArmure().label,
                niveauRequis(),
                statistiques().pointsVieMax(),
                statistiques().manaMax(),
                statistiques().attaquePhysique(),
                statistiques().puissanceMagique(),
                statistiques().armure(),
                statistiques().resistanceMagique(),
                statistiques().vitesse()
        );
    }

    @Override
    public int getOrder() {
        return OrderEquipement.ARMURE.getValue();
    }

    public int getArmureOrder(){
        return this.emplacementArmure.order;
    }
}

