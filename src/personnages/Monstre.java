package personnages;

import core.Statistiques;
import donjon.Difficulte;

import java.util.Random;

public class Monstre extends Personnage {
    private String type;

    private static final String YOUNG = "Jeune";

    private static final String[][] TYPE_NOM_MONSTRES = {
            {"Squelette", "Décomposé", "Brûlant", "Endurci"},
            {"Man-Bear-Pig", YOUNG, "Adulte", "Puissant"},
            {"Mimic", "Commun", "Peu Commun", "Rare"},
            {"Nain", "Berbe", "Digène", "Posteur"},
            {"Elfe", "Des Bois", "Guerrier", "Ancien"},
            {"Darkling", "Petit", "Grandissant", "Grand"},
            {"Homme-Bête", YOUNG, "Adulte", "Ancien"},
            {"Orc", "Grunt", "Garde de Sang", "Champion"},
            {"Humain", "Scout", "Coureur", "Assassin"},
            {"Géant", "Des Collines", "Des Montagnes", "Des Glaces"},
            {"Araignée", YOUNG, "Laineuse", "Grande"},
            {"Centaure", "Coureur", "Scout", "Patriarche"}
    };

    public Monstre() {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);

        super(TYPE_NOM_MONSTRES[randType][0]);

        type = TYPE_NOM_MONSTRES[randType][randNom];
        niveau = 1;

        inventaire.setMonnaie(rand.nextInt(0, 100000));
    }

    public Monstre(int niveauHero) {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);


        int niveauMonstre = niveauHero + rand.nextInt(0, 11);
        int pointsVieMax = 100 + (niveauMonstre * 10);
        int manaMax = 100 + (niveauMonstre * 10);
        int attaquePhysique = 10 + (niveauMonstre * 2);
        int puissanceMagique = 10 + (niveauMonstre * 2);
        int armure = 2 + (niveauMonstre * 2);
        int resistanceMagique = 2 + (niveauMonstre * 2);
        int vitesse = 100 + (niveauMonstre * 10);

        super(TYPE_NOM_MONSTRES[randType][0], new Statistiques(pointsVieMax, manaMax, attaquePhysique, puissanceMagique, armure, resistanceMagique, vitesse), niveauMonstre, Archetype.GUERRIER);
        type = TYPE_NOM_MONSTRES[randType][randNom];

        inventaire.setMonnaie(rand.nextInt(0, 100000));
    }

    public Monstre(int niveauHero, Difficulte difficulte) {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);


        int niveauMonstre = niveauHero + switch (difficulte) {
            case FACILE -> rand.nextInt(0, 5);
            case MOYEN -> rand.nextInt(0, 10);
            case DIFFICILE -> rand.nextInt(0, 15);
            case MORTEL -> rand.nextInt(0, 20);
        };
        int pointsVieMax = 100 + (niveauMonstre * 10);
        int manaMax = 100 + (niveauMonstre * 10);
        int attaquePhysique = 10 + (niveauMonstre * 2);
        int puissanceMagique = 10 + (niveauMonstre * 2);
        int armure = 2 + (niveauMonstre * 2);
        int resistanceMagique = 2 + (niveauMonstre * 2);
        int vitesse = 100 + (niveauMonstre * 10);

        super(TYPE_NOM_MONSTRES[randType][0], new Statistiques(pointsVieMax, manaMax, attaquePhysique, puissanceMagique, armure, resistanceMagique, vitesse), niveauMonstre, Archetype.GUERRIER);
        type = TYPE_NOM_MONSTRES[randType][randNom];

        inventaire.setMonnaie(rand.nextInt(0, 100000));
    }

    public Monstre(Statistiques statistiques, int niveau) {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);

        super(TYPE_NOM_MONSTRES[randType][0], statistiques, niveau, Archetype.GUERRIER);
        this.type = TYPE_NOM_MONSTRES[randType][randNom];

        inventaire.setMonnaie(rand.nextInt(0, 100000));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
