package equipements;

public enum EmplacementArmure {
    TETE("Tête", 1),
    EPAULES("Epaules", 2),
    TORSE("Torse", 3),
    POIGNETS("Poignets", 4),
    MAINS("Mains", 5),
    CEINTURE("Ceinture", 6),
    JAMBES("Jambes", 7),
    PIEDS("Pieds", 8);

    public final String label;
    public final int order;

    EmplacementArmure(String label, int order) {
        this.label = label;
        this.order = order;
    }

    public String getLabel() {
        return label;
    }

    public int getOrder() {
        return order;
    }
}
