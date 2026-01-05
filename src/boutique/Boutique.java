package boutique;

import consommables.Apothicaire;
import consommables.Consommable;
import consommables.Potions;
import equipements.Equipement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Boutique {
    private List<Equipement> equipements;
    private Map<Consommable, Integer> consommables;

    public Boutique() {
        equipements = new ArrayList<>();
        consommables = new HashMap<>(){{
            put(Apothicaire.ETAGERE.get(Potions.PDV_MINEURE),30);
            put(Apothicaire.ETAGERE.get(Potions.PDV_SUPERIEURE),30);
            put(Apothicaire.ETAGERE.get(Potions.PDV_MAJEURE),30);
        }};
    }
}