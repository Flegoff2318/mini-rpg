package consommables;

public enum Potions {

    PDV_MINEURE("potion de vie mineure"),
    PDV_SUPERIEURE("potion de vie superieure"),
    PDV_MAJEURE("potion de vie majeure"),
    PDM_MINEURE("potion de mana mineure"),
    PDM_SUPERIEURE("potion de mana superieure"),
    PDM_MAJEURE("potion de mana majeure"),
    ELIXIR_VIE("elixir de vie"),
    ELIXIR_MANA("elixir de mana");

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
