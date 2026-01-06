package equipements;

import java.util.HashMap;
import java.util.Map;

public interface Forgeron {
    Map<Armurerie, Equipement> RATELIER = new HashMap<>() {{
        put(Armurerie.FLAMBERGE, new Arme(Armurerie.FLAMBERGE.label, 200, 10, 4000
                , 2000, 50, 0));
        put(Armurerie.BATON_APPRENTI, new Arme(Armurerie.BATON_APPRENTI.label, 200, 1, 5000
                , 2500, 0, 20));
        put(Armurerie.EPEE_FER, new Arme(Armurerie.EPEE_FER.label, 100, 1, 5000
                , 2500, 20, 0));
        put(Armurerie.GOURDIN_GRAND_MUGUL, new Arme(Armurerie.GOURDIN_GRAND_MUGUL.label, 1, 100, 400000
                , 200000, 200, 1));
    }};
}
