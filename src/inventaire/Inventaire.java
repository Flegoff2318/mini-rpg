package inventaire;

import consommables.Potion;
import equipements.Equipement;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private int monnaie;
    private Map<Potion, Integer> consommables;
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

    public Map<Potion, Integer> getConsommables() {
        return consommables;
    }

    public Map<Equipement, Integer> getEquipements() {
        return equipements;
    }

    public void ajouterConsommables(Map<Potion, Integer> nouveauxConsommables) {
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

    public boolean ajouterConsommables(Potion potion, int nombre) {
        if (consommables.containsKey(potion)) {
            return consommables.put(potion, consommables.get(potion) + nombre) != null;
        } else {
            return consommables.put(potion, nombre) != null;
        }
    }

    public boolean retirerConsommables(Potion potion, int nombre) {
        int nouveauTotal = consommables.get(potion) - nombre;
        if (nouveauTotal < 0) return false;
        return consommables.put(potion, consommables.get(potion) - nombre) != null;
    }

    public boolean contientEquipement(Equipement equipement) {
        return equipements.getOrDefault(equipement, 0) > 0;
    }

    public void ajouterEquipement(Equipement equipement, int quantite) {
        if (quantite <= 0) {
            IO.println("La quantité doit être supérieure à 0.");
            return;
        }
        equipements.merge(equipement, quantite, Integer::sum);
    }

    public boolean retirerEquipement(Equipement equipement, int quantite) {
        if (quantite <= 0) {
            IO.println("La quantité doit être supérieure à 0.");
            return false;
        }
        Integer quantiteActuelle = equipements.get(equipement);
        if (quantiteActuelle == null || quantiteActuelle < quantite) {
            IO.println("Objet inexistant ou quantité insuffisante.");
            return false;
        }
        int quantiteRestante = quantiteActuelle - quantite;
        if (quantiteRestante == 0) {
            equipements.remove(equipement);
        } else {
            equipements.put(equipement, quantiteRestante);
        }
        return true;
    }
}
