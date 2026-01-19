package equipements;

import core.Statistiques;

import java.util.HashMap;
import java.util.Map;

public interface Forgeron {
    Map<Armurerie, Equipement> RATELIER = new HashMap<>() {{
        put(Armurerie.TETE_FER, new Armure(Armurerie.TETE_FER, Armurerie.TETE_FER.label, EmplacementArmure.TETE, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.EPAULES_FER, new Armure(Armurerie.EPAULES_FER, Armurerie.EPAULES_FER.label, EmplacementArmure.EPAULES, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.TORSE_FER, new Armure(Armurerie.TORSE_FER, Armurerie.TORSE_FER.label, EmplacementArmure.TORSE, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.POIGNETS_FER, new Armure(Armurerie.POIGNETS_FER, Armurerie.POIGNETS_FER.label, EmplacementArmure.POIGNETS, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.CEINTURE_FER, new Armure(Armurerie.CEINTURE_FER, Armurerie.CEINTURE_FER.label, EmplacementArmure.CEINTURE, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.MAINS_FER, new Armure(Armurerie.MAINS_FER, Armurerie.MAINS_FER.label, EmplacementArmure.MAINS, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.JAMBES_FER, new Armure(Armurerie.JAMBES_FER, Armurerie.JAMBES_FER.label, EmplacementArmure.JAMBES, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));
        put(Armurerie.PIEDS_FER, new Armure(Armurerie.PIEDS_FER, Armurerie.PIEDS_FER.label, EmplacementArmure.PIEDS, new Statistiques(10, 10, 2, 2, 1, 1, 0), 1, 4000, 2000));

        put(Armurerie.TETE_ARGENT, new Armure(Armurerie.TETE_ARGENT, Armurerie.TETE_ARGENT.label, EmplacementArmure.TETE, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.EPAULES_ARGENT, new Armure(Armurerie.EPAULES_ARGENT, Armurerie.EPAULES_ARGENT.label, EmplacementArmure.EPAULES, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.TORSE_ARGENT, new Armure(Armurerie.TORSE_ARGENT, Armurerie.TORSE_ARGENT.label, EmplacementArmure.TORSE, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.POIGNETS_ARGENT, new Armure(Armurerie.POIGNETS_ARGENT, Armurerie.POIGNETS_ARGENT.label, EmplacementArmure.POIGNETS, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.CEINTURE_ARGENT, new Armure(Armurerie.CEINTURE_ARGENT, Armurerie.CEINTURE_ARGENT.label, EmplacementArmure.CEINTURE, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.MAINS_ARGENT, new Armure(Armurerie.MAINS_ARGENT, Armurerie.MAINS_ARGENT.label, EmplacementArmure.MAINS, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.JAMBES_ARGENT, new Armure(Armurerie.JAMBES_ARGENT, Armurerie.JAMBES_ARGENT.label, EmplacementArmure.JAMBES, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));
        put(Armurerie.PIEDS_ARGENT, new Armure(Armurerie.PIEDS_ARGENT, Armurerie.PIEDS_ARGENT.label, EmplacementArmure.PIEDS, new Statistiques(25, 25, 5, 5, 2, 2, 0), 10, 40000, 20000));

        put(Armurerie.TETE_OR, new Armure(Armurerie.TETE_OR, Armurerie.TETE_OR.label, EmplacementArmure.TETE, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.EPAULES_OR, new Armure(Armurerie.EPAULES_OR, Armurerie.EPAULES_OR.label, EmplacementArmure.EPAULES, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.TORSE_OR, new Armure(Armurerie.TORSE_OR, Armurerie.TORSE_OR.label, EmplacementArmure.TORSE, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.POIGNETS_OR, new Armure(Armurerie.POIGNETS_OR, Armurerie.POIGNETS_OR.label, EmplacementArmure.POIGNETS, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.CEINTURE_OR, new Armure(Armurerie.CEINTURE_OR, Armurerie.CEINTURE_OR.label, EmplacementArmure.CEINTURE, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.MAINS_OR, new Armure(Armurerie.MAINS_OR, Armurerie.MAINS_OR.label, EmplacementArmure.MAINS, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.JAMBES_OR, new Armure(Armurerie.JAMBES_OR, Armurerie.JAMBES_OR.label, EmplacementArmure.JAMBES, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));
        put(Armurerie.PIEDS_OR, new Armure(Armurerie.PIEDS_OR, Armurerie.PIEDS_OR.label, EmplacementArmure.PIEDS, new Statistiques(50, 50, 10, 10, 5, 5, 0), 15, 400000, 200000));

        put(Armurerie.TETE_THORIUM, new Armure(Armurerie.TETE_THORIUM, Armurerie.TETE_THORIUM.label, EmplacementArmure.TETE, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.EPAULES_THORIUM, new Armure(Armurerie.EPAULES_THORIUM, Armurerie.EPAULES_THORIUM.label, EmplacementArmure.EPAULES, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.TORSE_THORIUM, new Armure(Armurerie.TORSE_THORIUM, Armurerie.TORSE_THORIUM.label, EmplacementArmure.TORSE, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.POIGNETS_THORIUM, new Armure(Armurerie.POIGNETS_THORIUM, Armurerie.POIGNETS_THORIUM.label, EmplacementArmure.POIGNETS, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.CEINTURE_THORIUM, new Armure(Armurerie.CEINTURE_THORIUM, Armurerie.CEINTURE_THORIUM.label, EmplacementArmure.CEINTURE, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.MAINS_THORIUM, new Armure(Armurerie.MAINS_THORIUM, Armurerie.MAINS_THORIUM.label, EmplacementArmure.MAINS, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.JAMBES_THORIUM, new Armure(Armurerie.JAMBES_THORIUM, Armurerie.JAMBES_THORIUM.label, EmplacementArmure.JAMBES, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));
        put(Armurerie.PIEDS_THORIUM, new Armure(Armurerie.PIEDS_THORIUM, Armurerie.PIEDS_THORIUM.label, EmplacementArmure.PIEDS, new Statistiques(100, 100, 40, 40, 20, 20, 100), 25, 4000000, 2000000));

        put(Armurerie.TETE_METEORE, new Armure(Armurerie.TETE_METEORE, Armurerie.TETE_METEORE.label, EmplacementArmure.TETE, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));
        put(Armurerie.EPAULES_METEORE, new Armure(Armurerie.EPAULES_METEORE, Armurerie.EPAULES_METEORE.label, EmplacementArmure.EPAULES, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));
        put(Armurerie.TORSE_METEORE, new Armure(Armurerie.TORSE_METEORE, Armurerie.TORSE_METEORE.label, EmplacementArmure.TORSE, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));
        put(Armurerie.POIGNETS_METEORE, new Armure(Armurerie.POIGNETS_METEORE, Armurerie.POIGNETS_METEORE.label, EmplacementArmure.POIGNETS, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 2000000));
        put(Armurerie.CEINTURE_METEORE, new Armure(Armurerie.CEINTURE_METEORE, Armurerie.CEINTURE_METEORE.label, EmplacementArmure.CEINTURE, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));
        put(Armurerie.MAINS_METEORE, new Armure(Armurerie.MAINS_METEORE, Armurerie.MAINS_METEORE.label, EmplacementArmure.MAINS, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));
        put(Armurerie.JAMBES_METEORE, new Armure(Armurerie.JAMBES_METEORE, Armurerie.JAMBES_METEORE.label, EmplacementArmure.JAMBES, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));
        put(Armurerie.PIEDS_METEORE, new Armure(Armurerie.PIEDS_METEORE, Armurerie.PIEDS_METEORE.label, EmplacementArmure.PIEDS, new Statistiques(200, 200, 100, 100, 100, 100, 200), 50, 40000000, 20000000));

        put(Armurerie.FLAMBERGE, new Arme(Armurerie.FLAMBERGE, Armurerie.FLAMBERGE.label, new Statistiques(0, 0, 50, 0, 0, 0, 20), 10, 10000, 5000));
        put(Armurerie.BATON_APPRENTI, new Arme(Armurerie.BATON_APPRENTI, Armurerie.BATON_APPRENTI.label, new Statistiques(0, 0, 0, 20, 0, 0, 10), 1, 10000, 5000));
        put(Armurerie.EPEE_FER, new Arme(Armurerie.EPEE_FER, Armurerie.EPEE_FER.label, new Statistiques(0, 0, 20, 0, 0, 0, 10), 1, 10000, 5000));
        put(Armurerie.EPEE_ARGENT, new Arme(Armurerie.EPEE_ARGENT, Armurerie.EPEE_ARGENT.label, new Statistiques(0, 0, 50, 0, 0, 0, 10), 10, 100000, 50000));
        put(Armurerie.EPEE_OR, new Arme(Armurerie.EPEE_OR, Armurerie.EPEE_OR.label, new Statistiques(0, 0, 100, 0, 0, 0, 10), 15, 1000000, 500000));
        put(Armurerie.EPEE_THORIUM, new Arme(Armurerie.EPEE_THORIUM, Armurerie.EPEE_THORIUM.label, new Statistiques(0, 0, 250, 0, 0, 0, 100), 25, 10000000, 5000000));
        put(Armurerie.EPEE_METEORE, new Arme(Armurerie.EPEE_METEORE, Armurerie.EPEE_METEORE.label, new Statistiques(0, 0, 500, 0, 0, 0, 200), 50, 100000000, 50000000));
        put(Armurerie.GOURDIN_GRAND_MUGUL, new Arme(Armurerie.GOURDIN_GRAND_MUGUL, Armurerie.GOURDIN_GRAND_MUGUL.label, new Statistiques(0, 0, 1000, 0, 0, 0, 1), 100, 400000, 200000));
    }};
}
