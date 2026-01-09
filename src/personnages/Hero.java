package personnages;

import core.Statistiques;

public class Hero extends Personnage {
    private int experience;

    public Hero(String nom) {
        super(nom);
        experience = 0;
    }

    public void gagnerExperience(int experience) {
        this.experience += experience;
        IO.println(String.format("Vous avez gagné %s d'expérience.", experience));
        levelUp();
    }

    public void levelUp() {
        int pallierLevelUp = (int) (10 * (Math.pow(2, this.niveau)));
        if (experience >= pallierLevelUp) {
            this.niveau += 1;
            this.experience = pallierLevelUp - this.experience;
            this.statistiques = this.getStatistiques().add(new Statistiques(10,10,1,1,1,1,10));
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
            this.experience = pallierLevelUp - this.experience;
            int experienceManquante = pallierLevelUp - this.experience;
            IO.println(String.format("Prochain niveau dans %s d'expérience.", experienceManquante));
        }
    }
}
