package equipements;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Armurerie {

    FLAMBERGE("Flamberge"),
    BATON_APPRENTI("Baton de l'apprenti sorcier"),
    EPEE_FER("Epee en fer"),
    TETE_FER("Casque en fer"),
    EPAULES_FER("Epaulieres en fer"),
    TORSE_FER("Plastron en fer"),
    POIGNETS_FER("Brassards en fer"),
    MAINS_FER("Gants en fer"),
    CEINTURE_FER("Ceinture en fer"),
    JAMBES_FER("Jambieres en fer"),
    PIEDS_FER("Bottes en fer"),
    GOURDIN_GRAND_MUGUL("Gourdin du Grand Mugul"),
    EPEE_METEORE("Epee Meteorique"),
    TETE_METEORE("Casque Meteorique"),
    EPAULES_METEORE("Epaulieres Meteorique"),
    TORSE_METEORE("Plastron Meteorique"),
    POIGNETS_METEORE("Brassards Meteorique"),
    MAINS_METEORE("Gants Meteorique"),
    CEINTURE_METEORE("Ceinture Meteorique"),
    JAMBES_METEORE("Jambieres Meteorique"),
    PIEDS_METEORE("Bottes Meteorique"),
    EPEE_THORIUM("Epee en Thorium"),
    TETE_THORIUM("Casque en Thorium"),
    EPAULES_THORIUM("Epaulieres en Thorium"),
    TORSE_THORIUM("Plastron en Thorium"),
    POIGNETS_THORIUM("Brassards en Thorium"),
    MAINS_THORIUM("Gants en Thorium"),
    CEINTURE_THORIUM("Ceinture en Thorium"),
    JAMBES_THORIUM("Jambieres en Thorium"),
    PIEDS_THORIUM("Bottes en Thorium"),
    EPEE_OR("Epee en Or"),
    TETE_OR("Casque en Or"),
    EPAULES_OR("Epaulieres en Or"),
    TORSE_OR("Plastron en Or"),
    POIGNETS_OR("Brassards en Or"),
    MAINS_OR("Gants en Or"),
    CEINTURE_OR("Ceinture en Or"),
    JAMBES_OR("Jambieres en Or"),
    PIEDS_OR("Bottes en Or"),
    EPEE_ARGENT("Epee en Argent"),
    TETE_ARGENT("Casque en Argent"),
    EPAULES_ARGENT("Epaulieres en Argent"),
    TORSE_ARGENT("Plastron en Argent"),
    POIGNETS_ARGENT("Brassards en Argent"),
    MAINS_ARGENT("Gants en Argent"),
    CEINTURE_ARGENT("Ceinture en Argent"),
    JAMBES_ARGENT("Jambieres en Argent"),
    PIEDS_ARGENT("Bottes en Argent");

    public final String label;

    Armurerie(String label) {
        this.label = label;
    }

    public static boolean contains(String test) {

        for (Armurerie a : Armurerie.values()) {
            if (a.label.equalsIgnoreCase(test)) {
                return true;
            }
        }

        return false;
    }

    public static Armurerie getByName(String name) {
        for (Armurerie a : Armurerie.values()) {
            if (a.label.equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public static Armurerie getRandomArmurerie() {
        Random rand = new Random();
        List<Armurerie> armurerieList = Arrays.stream(Armurerie.values()).toList();
        return armurerieList.get(rand.nextInt(armurerieList.size()));
    }
}
