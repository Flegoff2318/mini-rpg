package persistance.dto;

import java.util.Map;

public record InventaireDto(int monnaie, Map<String, Integer> consommables, Map<String, Integer> equipements) {
}
