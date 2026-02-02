package boutique;

import consommables.ApothicaireStandard;
import consommables.Potion;
import consommables.Potions;
import equipements.Armurerie;
import equipements.Equipement;
import equipements.ForgeronStandard;

import java.util.HashMap;
import java.util.Map;

public class Boutique {
    private final Map<Equipement, Integer> equipements;
    private final Map<Potion, Integer> consommables;

    public Boutique() {
        equipements = new HashMap<>();
        ForgeronStandard forgeronStandard = new ForgeronStandard();
        equipements.put(forgeronStandard.forger(Armurerie.FLAMBERGE), 1);
        equipements.put(forgeronStandard.forger(Armurerie.BATON_APPRENTI), 1);
        equipements.put(forgeronStandard.forger(Armurerie.GOURDIN_GRAND_MUGUL), 1);
        //Argent
        equipements.put(forgeronStandard.forger(Armurerie.EPEE_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.TETE_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.EPAULES_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.TORSE_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.POIGNETS_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.CEINTURE_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.MAINS_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.JAMBES_ARGENT), 1);
        equipements.put(forgeronStandard.forger(Armurerie.PIEDS_ARGENT), 1);
        //Thorium
        equipements.put(forgeronStandard.forger(Armurerie.EPEE_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.TETE_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.EPAULES_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.TORSE_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.POIGNETS_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.CEINTURE_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.MAINS_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.JAMBES_THORIUM), 1);
        equipements.put(forgeronStandard.forger(Armurerie.PIEDS_THORIUM), 1);
        consommables = new HashMap<>();
        ApothicaireStandard apothicaireStandard = new ApothicaireStandard();
        consommables.put(apothicaireStandard.melanger(Potions.PDV_MINEURE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.PDV_SUPERIEURE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.PDV_MAJEURE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.ELIXIR_VIE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.PDM_MINEURE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.PDM_SUPERIEURE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.PDM_MAJEURE), 30);
        consommables.put(apothicaireStandard.melanger(Potions.ELIXIR_MANA), 30);
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

    public void ajouterConsommables(Potion potion, int nombre) {
        if (consommables.containsKey(potion)) {
            consommables.put(potion, consommables.get(potion) + nombre);
        } else {
            consommables.put(potion, nombre);
        }
    }

    public boolean retirerConsommables(Potion potion, int nombre) {
        int nouveauTotal = consommables.get(potion) - nombre;
        if (nouveauTotal < 0) return false;
        return consommables.put(potion, consommables.get(potion) - nombre) != null;
    }

    public Map<Equipement, Integer> getEquipements() {
        return equipements;
    }

    public Map<Potion, Integer> getConsommables() {
        return consommables;
    }
}