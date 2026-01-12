package consommables;

import effets.EffetConsommableMana;
import effets.EffetConsommableSoins;

import java.util.HashMap;
import java.util.Map;

public interface Apothicaire {

    Map<Potions, Consommable> ETAGERE = new HashMap<>() {{
        put(Potions.PDV_MINEURE, new Consommable(Potions.PDV_MINEURE.label, 20, 100, 200, new EffetConsommableSoins()));
        put(Potions.PDV_SUPERIEURE, new Consommable(Potions.PDV_SUPERIEURE.label, 50, 250, 500, new EffetConsommableSoins()));
        put(Potions.PDV_MAJEURE, new Consommable(Potions.PDV_MAJEURE.label, 100, 500, 1000, new EffetConsommableSoins()));
        put(Potions.ELIXIR_VIE, new Consommable(Potions.ELIXIR_VIE.label, 200, 2000, 4000, new EffetConsommableSoins()));
        put(Potions.PDM_MINEURE, new Consommable(Potions.PDM_MINEURE.label, 20, 100, 200, new EffetConsommableMana()));
        put(Potions.PDM_SUPERIEURE, new Consommable(Potions.PDM_SUPERIEURE.label, 50, 250, 200, new EffetConsommableMana()));
        put(Potions.PDM_MAJEURE, new Consommable(Potions.PDM_MAJEURE.label, 100, 500, 1000, new EffetConsommableMana()));
        put(Potions.ELIXIR_MANA, new Consommable(Potions.ELIXIR_MANA.label, 200, 2000, 4000, new EffetConsommableMana()));
    }};


}
