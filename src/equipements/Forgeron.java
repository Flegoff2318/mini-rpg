package equipements;

import java.util.HashMap;
import java.util.Map;

public interface Forgeron {
    Map<Armurerie, Equipement> RATELIER = new HashMap<>() {{
        put(Armurerie.TETE_FER, new Armure(Armurerie.TETE_FER.label, 0, 1, 4000, 2000, 2, 0,EmplacementArmure.TETE));
        put(Armurerie.EPAULES_FER, new Armure(Armurerie.TETE_FER.label, 0, 1, 4000, 2000, 1, 0,EmplacementArmure.EPAULES));
        put(Armurerie.TORSE_FER, new Armure(Armurerie.TETE_FER.label, 0, 1, 4000, 2000, 3, 0,EmplacementArmure.TORSE));
        put(Armurerie.POIGNETS_FER, new Armure(Armurerie.TETE_FER.label, 0, 1, 4000, 2000, 1, 0,EmplacementArmure.POIGNETS));
        put(Armurerie.CEINTURE_FER, new Armure(Armurerie.TETE_FER.label, 0, 1, 4000, 2000, 1, 0,EmplacementArmure.CEINTURE));
        put(Armurerie.MAINS_FER, new Armure(Armurerie.TETE_FER.label, 10, 1, 4000, 2000, 1, 0,EmplacementArmure.MAINS));
        put(Armurerie.JAMBES_FER, new Armure(Armurerie.TETE_FER.label, 0, 1, 4000, 2000, 3, 0,EmplacementArmure.JAMBES));
        put(Armurerie.PIEDS_FER, new Armure(Armurerie.TETE_FER.label, 10, 1, 4000, 2000, 1, 0,EmplacementArmure.PIEDS));

        put(Armurerie.FLAMBERGE, new Arme(Armurerie.FLAMBERGE.label, 200, 10, 4000, 2000, 50, 0));
        put(Armurerie.BATON_APPRENTI, new Arme(Armurerie.BATON_APPRENTI.label, 200, 1, 5000, 2500, 0, 20));
        put(Armurerie.EPEE_FER, new Arme(Armurerie.EPEE_FER.label, 100, 1, 5000, 2500, 20, 0));
        put(Armurerie.GOURDIN_GRAND_MUGUL, new Arme(Armurerie.GOURDIN_GRAND_MUGUL.label, 1, 100, 400000, 200000, 200, 1));
    }};
}
