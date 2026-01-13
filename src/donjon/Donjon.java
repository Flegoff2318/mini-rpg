package donjon;

import equipements.Armurerie;
import equipements.Forgeron;
import personnages.Monstre;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Donjon {
    private List<Monstre> monstres;

    public Donjon(int nombreDeMonstres, int niveauHero) {
        monstres = new ArrayList<>();
        for (int i = 0; i < nombreDeMonstres; i++) {
            monstres.add(new Monstre(niveauHero));
        }
    }

    public Donjon(int nombreDeMonstres, int niveauHero, Difficulte difficulte) {
        monstres = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < nombreDeMonstres; i++) {
            Monstre nouveauMonstre = new Monstre(niveauHero, difficulte);
            if (random.nextInt(0, 101) > 20) {
                nouveauMonstre.getInventaire().ajouterEquipement(Forgeron.RATELIER.get(Armurerie.getRandomArmurerie()), 1);
            }
            monstres.add(nouveauMonstre);
        }
    }

    public Donjon(int nombreDeMonstres) {
        monstres = new ArrayList<>();
        for (int i = 0; i < nombreDeMonstres; i++) {
            monstres.add(new Monstre());
        }
    }

    public List<Monstre> getMonstres() {
        return monstres;
    }

    public void setMonstres(List<Monstre> monstres) {
        this.monstres = monstres;
    }
}
