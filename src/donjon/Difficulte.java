package donjon;

public enum Difficulte {
    FACILE("Facile"),
    MOYEN("Moyen"),
    DIFFICILE("Difficile"),
    MORTEL("Mortel");

    private final String label;

    Difficulte(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
