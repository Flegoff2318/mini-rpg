package sorts;

public enum Sorts {
    BOULE_DE_FEU("Boule de feu"),
    ECLAIR_DE_GIVRE("Eclair de givre"),
    ECLAIR_DE_FOUDRE("Eclair de foudre"),
    VAGUE_DE_LAVE("Vague de lave"),
    PERMAFROST("Permafrost"),
    ORAGE("Orage"),
    SOINS("Soins"),
    SOINS_SUPERIEUR("Soins superieur"),
    FRAPPE_HEROIQUE("Frappe heroique"),
    FRAPPE_SINISTRE("Frappe sinistre");

    public final String label;

    Sorts(String label) {
        this.label = label;
    }

    public static Sorts getByName(String name) {
        for (Sorts s : Sorts.values()) {
            if (s.label.equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
