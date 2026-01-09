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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
