package equipements;

import core.Statistiques;

import java.util.EnumMap;

public class EquipementEquipe {
    private Arme armeEquipee;
    private final EnumMap<EmplacementArmure, Armure> armuresEquipees = new EnumMap<>(EmplacementArmure.class);

    public Equipement equiper(Equipement equipement) {
        switch (equipement) {
            case Arme arme -> {
                return equiperArme(arme);
            }
            case Armure armure -> {
                return equiperArmure(armure);
            }
            default -> {
                return null;
            }
        }
//        if (equipement instanceof Arme nouvelleArme) {
//            return equiperArme(nouvelleArme);
//        }
//        if (equipement instanceof Armure nouvelleArmure) {
//            return equiper(nouvelleArmure);
//        }
//        return null;
    }

    public Equipement desequiper(Equipement equipement) {
        switch (equipement) {
            case Armure armure -> {
                return desequiperArmure(armure.emplacementArmure());
            }
            case Arme arme -> {
                return desequiperArme();
            }
            default -> {
                return null;
            }
        }
    }

    public Equipement equiperArme(Arme arme) {
        Arme ancienneArme = this.armeEquipee;
        this.armeEquipee = arme;
        return ancienneArme;
    }

    public Equipement desequiperArme() {
        Arme ancienneArme = this.armeEquipee;
        this.armeEquipee = null;
        return ancienneArme;
    }

    public Equipement equiperArmure(Armure armure) {
        return armuresEquipees.put(armure.emplacementArmure(), armure);
    }

    public Equipement desequiperArmure(EmplacementArmure emplacementArmure) {
        return armuresEquipees.remove(emplacementArmure);
    }

    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    public EnumMap<EmplacementArmure, Armure> getArmuresEquipees() {
        return armuresEquipees;
    }

    public Statistiques getBonusTotal() {
        Statistiques total = Statistiques.zero();
        if (armeEquipee != null) {
            total = total.add(armeEquipee.statistiques());
        }
        for (Armure armure : armuresEquipees.values()) {
            total = total.add(armure.statistiques());
        }
        return total;
    }
}
