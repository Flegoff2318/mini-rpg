package equipements;

import java.util.EnumMap;
import java.util.stream.IntStream;

public class EquipementEquipe {
    private Arme armeEquipee;
    private final EnumMap<EmplacementArmure, Armure> armuresEquipees = new EnumMap<>(EmplacementArmure.class);

    public void equiperArme(Arme arme) {
        this.armeEquipee = arme;
    }

    public void desequiperArme() {
        this.armeEquipee = null;
    }

    public void equiperArmure(Armure armure) {
        armuresEquipees.put(armure.getEmplacementArmure(), armure);
    }

    public void desequiperArmure(EmplacementArmure emplacementArmure) {
        armuresEquipees.remove(emplacementArmure);
    }

    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    public EnumMap<EmplacementArmure, Armure> getArmuresEquipees() {
        return armuresEquipees;
    }

    public int getArmureBonus() {
        return armuresEquipees.values().stream()
                .flatMapToInt(armure -> IntStream.of(armure.getArmure()))
                .sum();
    }

    public int getResistanceMagiqueBonus() {
        return armuresEquipees.values().stream()
                .flatMapToInt(armure -> IntStream.of(armure.getResistanceMagique()))
                .sum();
    }

    public int getVitesseBonus() {
        return armuresEquipees.values().stream()
                .flatMapToInt(armure -> IntStream.of(armure.getVitesse()))
                .sum();
    }
}
