package inventaire;

import consommables.Consommable;
import equipements.Equipement;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private int monnaie;
    private Map<Consommable, Integer> consommables;
    private Map<Equipement, Integer> equipements;

    public Inventaire() {
        monnaie = 0;
        consommables = new HashMap<>();
        equipements = new HashMap<>();
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

    public Map<Equipement, Integer> getEquipements() {
        return equipements;
    }

    public void setEquipements(Map<Equipement, Integer> equipements) {
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

    public void ajouterEquipements(Map<Equipement, Integer> nouveauxEquipements) {
        nouveauxEquipements.forEach((c, i) ->
                {
                    if (equipements.containsKey(c)) {
                        equipements.put(c, equipements.get(c) + i);
                    } else {
                        equipements.put(c, i);
                    }
                }
        );
    }


    public void ajouterEquipements(Equipement equipement, int nombre) {
        if (equipements.containsKey(equipement)) {
            equipements.put(equipement, equipements.get(equipement) + nombre);
        } else {
            equipements.put(equipement, nombre);
        }
    }

    public boolean retirerEquipements(Equipement equipement, int nombre) {
        int nouveauTotal = equipements.get(equipement) - nombre;
        if (nouveauTotal < 0) return false;
        return equipements.put(equipement, equipements.get(equipement) - nombre) != null;
    }

    public boolean ajouterConsommables(Consommable consommable, int nombre) {
        if (consommables.containsKey(consommable)) {
            return consommables.put(consommable, consommables.get(consommable) + nombre) != null;
        } else {
            return consommables.put(consommable, nombre) != null;
        }
    }

    public boolean retirerConsommables(Consommable consommable, int nombre) {
        int nouveauTotal = consommables.get(consommable) - nombre;
        if (nouveauTotal < 0) return false;
        return consommables.put(consommable, consommables.get(consommable) - nombre) != null;
    }
}
