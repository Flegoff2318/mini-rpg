package consommables;

public enum Potions {

    PDV_MINEURE("potion de vie mineure"),
    PDV_SUPERIEURE("potion de vie superieure"),
    PDV_MAJEURE("potion de vie majeure");

    public final String label;

    Potions(String label) {
        this.label = label;
    }

    public static boolean contains(String test) {

        for (Potions c : Potions.values()) {
            if (c.label.equalsIgnoreCase(test)) {
                return true;
            }
        }

        return false;
    }

    public static Potions getByName(String name) {
        for (Potions c : Potions.values()) {
            if (c.label.equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}
