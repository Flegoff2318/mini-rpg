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

    public final String label;

    EmplacementArmure(String label) {
        this.label = label;
    }
}
