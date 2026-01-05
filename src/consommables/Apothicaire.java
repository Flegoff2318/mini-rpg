package consommables;

import effets.EffetConsommableSoins;

import java.util.HashMap;
import java.util.Map;

public interface Apothicaire {

    Map<Potions, Consommable> ETAGERE = new HashMap<>() {{
        put(Potions.PDV_MINEURE, new Consommable("potion de vie mineure", 20, 100, 200, new EffetConsommableSoins()));
        put(Potions.PDV_SUPERIEURE, new Consommable("potion de vie superieure", 50, 250, 500, new EffetConsommableSoins()));
        put(Potions.PDV_MAJEURE, new Consommable("potion de vie majeure", 100, 500, 1000, new EffetConsommableSoins()));
    }};


}
