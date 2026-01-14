package sorts;

import effets.EffetSortDegats;
import effets.EffetSortSoins;
import personnages.Archetype;

import java.util.HashMap;
import java.util.Map;

public interface Grimoire {
    Map<String, Sort> SORTS = new HashMap<>() {{
        put("boule de feu", new Sort("boule de feu", 5, 10, 1, new EffetSortDegats(), null));
        put("eclair de givre", new Sort("eclair de givre", 10, 20, 3, new EffetSortDegats(), null));
        put("eclair de foudre", new Sort("eclair de foudre", 15, 30, 5, new EffetSortDegats(), null));
        put("vague de lave", new Sort("vague de lave", 20, 40, 7, new EffetSortDegats(), null));
        put("permafrost", new Sort("permafrost", 25, 50, 9, new EffetSortDegats(), null));
        put("orage", new Sort("orage", 30, 60, 11, new EffetSortDegats(), null));
        put("soins", new Sort("soins", 10, 20, 1, new EffetSortSoins(), null));
        put("soins superieur", new Sort("soins superieur", 25, 50, 10, new EffetSortSoins(), null));
        put("frappe heroique", new Sort("frappe heroique", 0,30,1,new EffetSortDegats(), Archetype.GUERRIER));
        put("frappe sinistre", new Sort("frappe sinistre", 0,30,1,new EffetSortDegats(), Archetype.ASSASSIN));
    }};
}
