package personnages;

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
            niveau += 1;
            experience = pallierLevelUp - experience;
            attaque+=1;
            armure += 1;
            resistanceMagique += 1;
            pointsVieMax += 10;
            pointsVie = pointsVieMax;
            manaMax += 10;
            mana = manaMax;
            vitesse += 10;
            IO.println("Level up !");
            IO.println(String.format("Niveau %s : Vos points de vie et de mana ont été régénérés.", niveau));
            IO.println(String.format("- Armure : %s", armure));
            IO.println(String.format("- Attaque : %s", attaque));
            IO.println(String.format("- Résistance magique : %s", resistanceMagique));
            IO.println(String.format("- Points de vie totaux : %s", pointsVieMax));
            IO.println(String.format("- Points de mana totaux : %s", mana));
            IO.println(String.format("- Vitesse : %s", vitesse));
        } else {
            experience = pallierLevelUp - experience;
            int experienceManquante = pallierLevelUp - experience;
            IO.println(String.format("Prochain niveau dans %s d'expérience.", experienceManquante));
        }
    }
}
