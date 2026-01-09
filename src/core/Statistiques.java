package core;

public record Statistiques(int pointsVieMax,
                           int manaMax,
                           int attaquePhysique,
                           int puissanceMagique,
                           int armure,
                           int resistanceMagique,
                           int vitesse) {
    public Statistiques add(Statistiques s) {
        return new Statistiques(
                pointsVieMax + s.pointsVieMax,
                manaMax + s.manaMax,
                attaquePhysique + s.attaquePhysique,
                puissanceMagique + s.puissanceMagique,
                armure + s.armure,
                resistanceMagique + s.resistanceMagique,
                vitesse + s.vitesse
        );
    }

    public static Statistiques zero() {
        return new Statistiques(0, 0, 0, 0, 0, 0, 0);
    }
}
