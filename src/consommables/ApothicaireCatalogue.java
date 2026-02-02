package consommables;

import effets.EffetConsommableMana;
import effets.EffetConsommableSoins;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class ApothicaireCatalogue {
    private static final EnumMap<Potions, Potion> ETAGERE_MUTABLE = new EnumMap<>(Potions.class);
    private static final Map<Potions, Potion> ETAGERE;
    
    static {
        ETAGERE_MUTABLE.put(Potions.PDV_MINEURE, new Potion(Potions.PDV_MINEURE, Potions.PDV_MINEURE.label, 20, 100, 200, new EffetConsommableSoins()));
        ETAGERE_MUTABLE.put(Potions.PDV_SUPERIEURE, new Potion(Potions.PDV_SUPERIEURE,Potions.PDV_SUPERIEURE.label, 50, 250, 500, new EffetConsommableSoins()));
        ETAGERE_MUTABLE.put(Potions.PDV_MAJEURE, new Potion(Potions.PDV_MAJEURE,Potions.PDV_MAJEURE.label, 100, 500, 1000, new EffetConsommableSoins()));
        ETAGERE_MUTABLE.put(Potions.ELIXIR_VIE, new Potion(Potions.ELIXIR_VIE,Potions.ELIXIR_VIE.label, 200, 2000, 4000, new EffetConsommableSoins()));
        ETAGERE_MUTABLE.put(Potions.PDM_MINEURE, new Potion(Potions.PDM_MINEURE,Potions.PDM_MINEURE.label, 20, 100, 200, new EffetConsommableMana()));
        ETAGERE_MUTABLE.put(Potions.PDM_SUPERIEURE, new Potion(Potions.PDM_SUPERIEURE,Potions.PDM_SUPERIEURE.label, 50, 250, 200, new EffetConsommableMana()));
        ETAGERE_MUTABLE.put(Potions.PDM_MAJEURE, new Potion(Potions.PDM_MAJEURE,Potions.PDM_MAJEURE.label, 100, 500, 1000, new EffetConsommableMana()));
        ETAGERE_MUTABLE.put(Potions.ELIXIR_MANA, new Potion(Potions.ELIXIR_MANA,Potions.ELIXIR_MANA.label, 200, 2000, 4000, new EffetConsommableMana()));

        ETAGERE = Collections.unmodifiableMap(ETAGERE_MUTABLE);
    }

    private ApothicaireCatalogue(){

    }

    public static Map<Potions,Potion> getEtagere(){
        return ETAGERE;
    }
}
