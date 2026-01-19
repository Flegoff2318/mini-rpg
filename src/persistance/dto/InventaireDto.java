package persistance.dto;

import java.util.Map;

public class InventaireDto {
    public int monnaie;
    // key = Armurerie.name(), value = quantité
    public Map<String, Integer> consommables;
    // key = Potions.name(), value = quantité
    public Map<String, Integer> equipements;

    public InventaireDto() {
    }
}
