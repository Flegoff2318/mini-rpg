package equipements;

public enum Armurerie {

    FLAMBERGE("Flamberge"),
    BATON_APPRENTI("Baton de l'apprenti sorcier"),
    EPEE_FER("Epee en fer"),
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
