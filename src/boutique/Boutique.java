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
    private final Map<Equipement, Integer> equipements;
    private final Map<Consommable, Integer> consommables;

    public Boutique() {
        equipements = new HashMap<>() {{
            put(Forgeron.RATELIER.get(Armurerie.FLAMBERGE), 1);
            put(Forgeron.RATELIER.get(Armurerie.BATON_APPRENTI), 1);
            put(Forgeron.RATELIER.get(Armurerie.GOURDIN_GRAND_MUGUL), 1);
            //Argent
            put(Forgeron.RATELIER.get(Armurerie.EPEE_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.TETE_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.EPAULES_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.TORSE_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.POIGNETS_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.CEINTURE_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.MAINS_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.JAMBES_ARGENT),1);
            put(Forgeron.RATELIER.get(Armurerie.PIEDS_ARGENT),1);
            //Thorium
            put(Forgeron.RATELIER.get(Armurerie.EPEE_THORIUM), 1);
            put(Forgeron.RATELIER.get(Armurerie.TETE_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.EPAULES_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.TORSE_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.POIGNETS_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.CEINTURE_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.MAINS_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.JAMBES_THORIUM),1);
            put(Forgeron.RATELIER.get(Armurerie.PIEDS_THORIUM),1);
        }};
        consommables = new HashMap<>() {{
            put(Apothicaire.ETAGERE.get(Potions.PDV_MINEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDV_SUPERIEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDV_MAJEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.ELIXIR_VIE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDM_MINEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDM_SUPERIEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.PDM_MAJEURE), 30);
            put(Apothicaire.ETAGERE.get(Potions.ELIXIR_MANA), 30);
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