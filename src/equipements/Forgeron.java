package equipements;

import core.Statistiques;

import java.util.HashMap;
import java.util.Map;

public interface Forgeron {
    Map<Armurerie, Equipement> RATELIER = new HashMap<>() {{
        put(Armurerie.TETE_FER, new Armure(Armurerie.TETE_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.EPAULES_FER, new Armure(Armurerie.EPAULES_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.TORSE_FER, new Armure(Armurerie.TORSE_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.POIGNETS_FER, new Armure(Armurerie.POIGNETS_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.CEINTURE_FER, new Armure(Armurerie.CEINTURE_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.MAINS_FER, new Armure(Armurerie.MAINS_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.JAMBES_FER, new Armure(Armurerie.JAMBES_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.PIEDS_FER, new Armure(Armurerie.PIEDS_FER.label, EmplacementArmure.TETE, new Statistiques(0, 0, 0, 0, 1, 1, 0), 1, 4000, 2000));

        put(Armurerie.FLAMBERGE, new Arme(Armurerie.FLAMBERGE.label, new Statistiques(0, 0, 50, 0, 0, 0, 20), 10, 10000, 5000));
        put(Armurerie.BATON_APPRENTI, new Arme(Armurerie.BATON_APPRENTI.label, new Statistiques(0, 0, 0, 20, 0, 0, 10), 1, 10000, 5000));
        put(Armurerie.EPEE_FER, new Arme(Armurerie.EPEE_FER.label, new Statistiques(0, 0, 20, 0, 0, 0, 10), 1, 10000, 5000));
        put(Armurerie.GOURDIN_GRAND_MUGUL, new Arme(Armurerie.GOURDIN_GRAND_MUGUL.label, new Statistiques(0, 0, 100, 0, 0, 0, 1), 100, 400000, 200000));
    }};
}
