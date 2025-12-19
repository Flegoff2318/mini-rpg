package inventaire;

import consommables.Consommable;
import equipements.Equipement;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {
    private int monnaie;
    private List<Consommable> consommables;
    private List<Equipement> equipements;

    public Inventaire() {
        monnaie = 0;
        consommables = new ArrayList<>();
        equipements = new ArrayList<>();
    }

    public void ajouterMonnaie(int monnaie) {
        this.monnaie += monnaie;
    }

    public void retirerMonnaie(int monnaie) {
        if (this.monnaie - monnaie <= 0) {
            IO.println("Vous n'avez pas les fonds disponibles.");
        } else {
            this.monnaie -= monnaie;
        }
    }

    public int getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(int monnaie) {
        this.monnaie = monnaie;
    }

    public List<Consommable> getConsommables() {
        return consommables;
    }

    public void setConsommables(List<Consommable> consommables) {
        this.consommables = consommables;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }
}
