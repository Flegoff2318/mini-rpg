package persistance.dto;

public record HeroDto(String nom, int experience, int niveau, int pointsVie, int mana, InventaireDto inventaire,
                      EquipementEquipeDto equipementEquipe, StatistiquesDto statistiques, ArchetypeDto archetype) {

}
