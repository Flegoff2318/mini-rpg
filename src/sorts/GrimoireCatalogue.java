package sorts;

import effets.EffetSortDegats;
import effets.EffetSortSoins;
import personnages.Archetype;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class GrimoireCatalogue {
    private static final EnumMap<Sorts, Sort> SORTS_MUTABLE = new EnumMap<>(Sorts.class);
    private static final Map<Sorts, Sort> SORTS;

    static {
        SORTS_MUTABLE.put(Sorts.BOULE_DE_FEU, new Sort("boule de feu", 5, 10, 1, new EffetSortDegats(), null));
        SORTS_MUTABLE.put(Sorts.ECLAIR_DE_GIVRE, new Sort("eclair de givre", 10, 20, 3, new EffetSortDegats(), null));
        SORTS_MUTABLE.put(Sorts.ECLAIR_DE_FOUDRE, new Sort("eclair de foudre", 15, 30, 5, new EffetSortDegats(), null));
        SORTS_MUTABLE.put(Sorts.VAGUE_DE_LAVE, new Sort("vague de lave", 20, 40, 7, new EffetSortDegats(), null));
        SORTS_MUTABLE.put(Sorts.PERMAFROST, new Sort("permafrost", 25, 50, 9, new EffetSortDegats(), null));
        SORTS_MUTABLE.put(Sorts.ORAGE, new Sort("orage", 30, 60, 11, new EffetSortDegats(), null));
        SORTS_MUTABLE.put(Sorts.SOINS, new Sort("soins", 10, 20, 1, new EffetSortSoins(), null));
        SORTS_MUTABLE.put(Sorts.SOINS_SUPERIEUR, new Sort("soins superieur", 25, 50, 10, new EffetSortSoins(), null));
        SORTS_MUTABLE.put(Sorts.FRAPPE_HEROIQUE, new Sort("frappe heroique", 0, 30, 1, new EffetSortDegats(), Archetype.GUERRIER));
        SORTS_MUTABLE.put(Sorts.FRAPPE_SINISTRE, new Sort("frappe sinistre", 0, 30, 1, new EffetSortDegats(), Archetype.ASSASSIN));

        SORTS = Collections.unmodifiableMap(SORTS_MUTABLE);
    }

    private GrimoireCatalogue() {

    }

    public static Map<Sorts, Sort> getSorts() {
        return SORTS;
    }
}
