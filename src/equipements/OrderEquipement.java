package equipements;

public enum OrderEquipement {
    ARME(1),
    ARMURE(2),
    LAST(Integer.MAX_VALUE);

    private final int value;

    OrderEquipement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
