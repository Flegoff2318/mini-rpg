package inventaire;

import consommables.Consommable;
import equipements.Equipement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventaire {
    private int monnaie;
    private Map<Consommable, Integer> consommables;
    private List<Equipement> equipements;

    public Inventaire() {
        monnaie = 0;
        consommables = new HashMap<Consommable, Integer>();
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

    public Map<Consommable, Integer> getConsommables() {
        return consommables;
    }

    public void setConsommables(Map<Consommable, Integer> consommables) {
        this.consommables = consommables;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    public void ajouterConsommables(Map<Consommable, Integer> nouveauxConsommables) {
        nouveauxConsommables.forEach((c, i) ->
                {
                    if (consommables.containsKey(c)) {
                        consommables.put(c, consommables.get(c) + i);
                    } else {
                        consommables.put(c, i);
                    }
                }
        );
    }

    public boolean supprimerConsommable(Consommable consommable) {
        int nouveauTotal = consommables.get(consommable) - 1;
        return consommables.put(consommable, nouveauTotal) != null;
    }

    public void supprimerConsommables(Map<Consommable, Integer> consommablesASupprimer) {
        consommablesASupprimer.forEach((c, i) ->
                {
                    int nouveauTotal = consommables.get(c) - i;
                    if (nouveauTotal <= 0)
                        nouveauTotal = 0;
                    consommables.put(c, nouveauTotal);
                }
        );
    }
}
