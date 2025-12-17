package donjon;

import personnages.Monstre;

import java.util.ArrayList;
import java.util.List;

public class Donjon {
    private List<Monstre> monstres;

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
