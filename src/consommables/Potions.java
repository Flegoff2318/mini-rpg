package consommables;

public enum Potions {
    PDV_MINEURE("potion de vie mineure"),
    PDV_SUPERIEURE("potion de vie superieure"),
    PDV_MAJEURE("potion de vie majeure");
    public final String label;
    Potions(String label){
        this.label=label;
    }
}
