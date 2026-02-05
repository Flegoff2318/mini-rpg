package inventaire.helpers;

import equipements.*;
import inventaire.Inventaire;
import inventaire.entry.ItemEntry;

import java.util.List;
import java.util.Map;


public class InventaireViewBuilder {
    private InventaireViewBuilder() {

    }

    public static List<ItemEntry> buildEquipementsView(Inventaire inventaire) {
        return inventaire.getEquipements().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> new ItemEntry(entry.getKey(), entry.getValue()))
                .sorted(EquipementComparators.byTypeLevelName())
                .toList();
    }

    public static List<Arme> buildEquipementsArmeView(Inventaire inventaire) {
        return inventaire.getEquipements().keySet().stream()
                .filter(Arme.class::isInstance)
                .map(Arme.class::cast)
                .sorted(EquipementComparators.armeByLevelName())
                .toList();
    }

    public static List<Armure> buildEquipementsArmureView(Inventaire inventaire) {
        return inventaire.getEquipements().keySet().stream()
                .filter(Armure.class::isInstance)
                .map(Armure.class::cast)
                .sorted(EquipementComparators.armureBySlotLevelName())
                .toList();
    }

    public static List<Armure> buildEquipementsArmureView(EquipementEquipe equipementEquipe) {
        return equipementEquipe.getArmuresEquipees().values().stream()
                .sorted(EquipementComparators.armureBySlotLevelName())
                .toList();
    }

    public static List<Armure> buildEquipementsArmureViewBySlot(Inventaire inventaire, EmplacementArmure emplacementArmure) {
        return inventaire.getEquipements().keySet().stream()
                .filter(Armure.class::isInstance)
                .map(Armure.class::cast)
                .filter(armure -> armure.emplacementArmure() == emplacementArmure)
                .sorted(EquipementComparators.armureByLevelName())
                .toList();
    }

    public static List<ItemEntry> buildEquipementsView(Map<Equipement,Integer> equipements){
        return equipements.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> new ItemEntry(entry.getKey(), entry.getValue()))
                .sorted(EquipementComparators.byTypeLevelName())
                .toList();
    }
}
