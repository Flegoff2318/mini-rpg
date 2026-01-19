package personnages;

import core.Statistiques;

public class Hero extends Personnage {
    private int experience;

    public Hero(String nom) {
        super(nom);
        experience = 0;
    }

    public Hero(String nom, Archetype archetype) {
        super(nom, archetype);
        this.experience = 0;
    }

    public Hero(String nom, int niveau, int experience) {
        super(nom);

    }

    public Hero(String nom, int niveau, Archetype archetype, int experience) {
        super(nom, niveau, archetype);
        this.experience = experience;
    }

    public void gagnerExperience(int experience) {
        this.experience += experience;
        IO.println(String.format("Vous avez gagné %s d'expérience.", experience));
        levelUp();
    }

    public void levelUp() {
        boolean levelingDone = false;
        while (!levelingDone) {
            int pallierLevelUp = (int) (10 * (Math.pow(2, this.niveau)));
            if (experience >= pallierLevelUp) {
                this.niveau += 1;
                this.experience = this.experience - pallierLevelUp;
                int pointsVieMax = 0;
                int manaMax = 0;
                int attaquePhysique = 0;
                int puissanceMagique = 0;
                int armure = 0;
                int resistanceMagique = 0;
                int vitesse = 0;
                switch (this.archetype) {
                    case MAGE -> {
                        pointsVieMax = 15;
                        manaMax = 25;
                        attaquePhysique = 2;
                        puissanceMagique = 5;
                        armure = 1;
                        resistanceMagique = 3;
                        vitesse = 10;
                    }
                    case ASSASSIN -> {
                        pointsVieMax = 20;
                        manaMax = 20;
                        attaquePhysique = 5;
                        puissanceMagique = 2;
                        armure = 2;
                        resistanceMagique = 2;
                        vitesse = 20;
                    }
                    case GUERRIER -> {
                        pointsVieMax = 30;
                        manaMax = 10;
                        attaquePhysique = 8;
                        puissanceMagique = 1;
                        armure = 3;
                        resistanceMagique = 1;
                        vitesse = 10;
                    }
                }
                this.statistiques = this.getStatistiques().add(new Statistiques(pointsVieMax, manaMax, attaquePhysique, puissanceMagique, armure, resistanceMagique, vitesse));
                this.pointsVie = this.getPointsVieMax();
                this.mana = this.getManaMax();
                IO.println("Level up !");
                IO.println(String.format("Niveau %s : Vos points de vie et de mana ont été régénérés.", this.niveau));
                IO.println(String.format("- Armure : %s", this.getStatsEffectives().armure()));
                IO.println(String.format("- Attaque : %s", this.getStatsEffectives().attaquePhysique()));
                IO.println(String.format("- Résistance magique : %s", this.getStatsEffectives().resistanceMagique()));
                IO.println(String.format("- Points de vie totaux : %s", this.getPointsVieMax()));
                IO.println(String.format("- Points de mana totaux : %s", this.getManaMax()));
                IO.println(String.format("- Vitesse : %s", this.getStatsEffectives().vitesse()));
            } else {
                int experienceManquante = pallierLevelUp - this.experience;
                IO.println(String.format("Prochain niveau dans %s d'expérience.", experienceManquante));
                levelingDone = true;
            }
        }

    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
