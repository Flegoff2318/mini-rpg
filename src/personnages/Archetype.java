package personnages;

public enum Archetype {
    MAGE("Mage"),
    ASSASSIN("Assassin"),
    GUERRIER("Guerrier");

    public final String label;

    Archetype(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
