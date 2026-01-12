package personnages;

import java.util.Random;

import static personnages.Bestiaire.BESTIAIRE;

public class Monstre extends Personnage {
    private String type;

    public Monstre() {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);

        super(BESTIAIRE[randType][0]);

        type = BESTIAIRE[randType][randNom];
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

        super(BESTIAIRE[randType][0], pointsVieMax, manaMax, attaquePhysique, puissanceMagique, armure, resistanceMagique, vitesse, niveauMonstre, Archetype.GUERRIER);
        type = BESTIAIRE[randType][randNom];

        inventaire.setMonnaie(rand.nextInt(0, 100000));
    }

    public Monstre(int pointsVieMax, int manaMax, int attaquePhysique, int puissanceMagique, int armure, int resistanceMagique, int vitesse, int niveau) {
        Random rand = new Random();
        int randNom = rand.nextInt(1, 4);
        int randType = rand.nextInt(0, 12);

        super(BESTIAIRE[randType][0], pointsVieMax, manaMax, attaquePhysique, puissanceMagique, armure, resistanceMagique, vitesse, niveau, Archetype.GUERRIER);
        this.type = BESTIAIRE[randType][randNom];

        inventaire.setMonnaie(rand.nextInt(0, 100000));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
