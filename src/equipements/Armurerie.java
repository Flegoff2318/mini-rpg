package equipements;

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
    GOURDIN_GRAND_MUGUL("Gourdin du Grand Mugul");

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
}
