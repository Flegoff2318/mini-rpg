package personnages;

import inventaire.Inventaire;

import java.util.ArrayList;
import java.util.Random;

import static personnages.Bestiaire.BESTIAIRE;

public class Monstre extends Personnage {
    private String type;

    public Monstre() {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);

        this.nom = BESTIAIRE[randType][0];
        type = BESTIAIRE[randType][randNom];
        pointsVieMax = 100;
        pointsVie = 100;
        manaMax = 100;
        mana = 100;
        attaque = 10;
        armure = 2;
        resistanceMagique = 2;
        niveau = 1;
        vitesse = 100;
        inventaire = new Inventaire();
        // Génération du butin
        inventaire.setMonnaie(rand.nextInt(0, 100000));
        // Génération des sorts
        sorts = new ArrayList<>();
    }

    public Monstre(String nom, String type) {
        super(nom);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
