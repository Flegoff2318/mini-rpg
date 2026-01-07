package equipements;

public enum EmplacementArmure {
    TETE("Tête"),
    EPAULES("Epaules"),
    TORSE("Torse"),
    POIGNETS("Poignets"),
    MAINS("Mains"),
    CEINTURE("Ceinture"),
    JAMBES("Jambes"),
    PIEDS("Pieds");

    private final String label;

    EmplacementArmure(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
