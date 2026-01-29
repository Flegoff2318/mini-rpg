package equipements;


import core.Statistiques;

public record Arme(Armurerie type, String nom, Statistiques statistiques, int niveauRequis, int prixAchat,
                   int prixVente) implements Equipement {
    @Override
    public String toString() {
        return String.format("%s - LEVEL : [%s] : HP:%s, MANA:%s, ATK:%s, MAG:%s, ARMOR:%s, MR:%s, VIT:%s",
                nom(),
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
        return OrderEquipement.ARME.getValue();
    }
}
