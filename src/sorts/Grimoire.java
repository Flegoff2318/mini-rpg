package sorts;

import effets.EffetSortDegats;
import effets.EffetSortSoins;

import java.util.HashMap;
import java.util.Map;

public interface Grimoire {
    Map<String, Sort> SORTS = new HashMap<>() {{
        put("boule de feu", new Sort("boule de feu", 5, 10, 1, new EffetSortDegats()));
        put("eclair de givre", new Sort("eclair de givre", 10, 20, 3, new EffetSortDegats()));
        put("eclair de foudre", new Sort("eclair de foudre", 15, 30, 5, new EffetSortDegats()));
        put("vague de lave", new Sort("vague de lave", 20, 40, 7, new EffetSortDegats()));
        put("permafrost", new Sort("permafrost", 25, 50, 9, new EffetSortDegats()));
        put("orage", new Sort("orage", 30, 60, 11, new EffetSortDegats()));
        put("soins", new Sort("soins", 10, 20, 1, new EffetSortSoins()));
        put("soins superieur", new Sort("soins superieur", 25, 50, 10, new EffetSortSoins()));
    }};
}
