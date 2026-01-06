package boutique;

import consommables.Apothicaire;
import consommables.Consommable;
import consommables.Potions;
import equipements.Armurerie;
import equipements.Equipement;
import equipements.Forgeron;

import java.util.HashMap;
import java.util.Map;

public class Boutique {
    private Map<Equipement, Integer> equipements;
    private Map<Consommable, Integer> consommables;

    public Boutique() {
        equipements = new HashMap<>() {{
            put(Forgeron.RATELIER.get(Armurerie.FLAMBERGE), 1);
            put(Forgeron.RATELIER.get(Armurerie.BATON_APPRENTI), 1);
            put(Forgeron.RATELIER.get(Armurerie.GOURDIN_GRAND_MUGUL), 1);
        }};
        consommables = new HashMap<>() {{
            put(Apothicaire.ETAGERE.get(Potions.PDV_MINEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDV_SUPERIEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDV_MAJEURE), 30);
        }};
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

    public void ajouterConsommables(Consommable consommable, int nombre) {
        if (consommables.containsKey(consommable)) {
            consommables.put(consommable, consommables.get(consommable) + nombre);
        } else {
            consommables.put(consommable, nombre);
        }
    }

    public boolean retirerConsommables(Consommable consommable, int nombre) {
        int nouveauTotal = consommables.get(consommable) - nombre;
        if (nouveauTotal < 0) return false;
        return consommables.put(consommable, consommables.get(consommable) - nombre) != null;
    }

    public Map<Equipement, Integer> getEquipements() {
        return equipements;
    }

    public Map<Consommable, Integer> getConsommables() {
        return consommables;
    }
}