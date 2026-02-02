package inventaire.helpers;

import equipements.Arme;
import equipements.Armure;
import equipements.Equipement;
import equipements.OrderEquipement;
import inventaire.entry.ItemEntry;

import java.util.Comparator;

public class EquipementComparators {
    private EquipementComparators() {

    }

    public static Comparator<ItemEntry> byTypeLevelName() {
        return Comparator.<ItemEntry>comparingInt(itemEntry -> typeOrder(itemEntry.equipement()))
                .thenComparingInt(itemEntry -> niveauRequis(itemEntry.equipement()))
                .thenComparing(itemEntry -> nom(itemEntry.equipement()));
    }

    public static Comparator<Arme> armeByLevelName() {
        return Comparator.comparingInt(Arme::niveauRequis)
                .thenComparing(Arme::nom);
    }

    public static Comparator<Armure> armureBySlotLevelName() {
        return Comparator.comparingInt(Armure::getArmureOrder)
                .thenComparingInt(Armure::niveauRequis)
                .thenComparing(Armure::nom);
    }

    public static Comparator<Armure> armureByLevelName() {
        return Comparator.comparingInt(Armure::niveauRequis)
                .thenComparing(Armure::nom);
    }

    private static int typeOrder(Equipement equipement) {
        if (equipement instanceof Arme arme) return arme.getOrder();
        if (equipement instanceof Armure armure) return armure.getOrder();
        return OrderEquipement.LAST.getValue();
    }

    private static int niveauRequis(Equipement equipement) {
        if (equipement instanceof Arme arme) return arme.niveauRequis();
        if (equipement instanceof Armure armure) return armure.niveauRequis();
        return OrderEquipement.LAST.getValue();
    }

    private static String nom(Equipement equipement) {
        return equipement.nom();
    }
}
