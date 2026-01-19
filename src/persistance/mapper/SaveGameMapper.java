package persistance.mapper;

import consommables.Apothicaire;
import consommables.Potion;
import consommables.Potions;
import core.Statistiques;
import equipements.*;
import inventaire.Inventaire;
import persistance.dto.*;
import personnages.Archetype;
import personnages.Hero;

import java.util.HashMap;
import java.util.Map;

public class SaveGameMapper {
    // --------------------
    // DOMAIN -> DTO (SAVE)
    // --------------------

    public SaveGameDto toDto(Hero hero) {
        SaveGameDto save = new SaveGameDto();
        save.hero = toHeroDto(hero);
        return save;
    }

    private HeroDto toHeroDto(Hero hero) {
        HeroDto dto = new HeroDto();
        dto.nom = hero.getNom();
        dto.niveau = hero.getNiveau();
        dto.pointsVie = hero.getPointsVie();
        dto.mana = hero.getMana();
        dto.experience = hero.getExperience();

        // Archetype
        dto.archetype = new ArchetypeDto();
        dto.archetype.name = hero.getArchetype().name();

        // Statistiques
        dto.statistiques = toStatistiquesDto(hero.getStatistiques());

        // Inventaire
        dto.inventaire = toInventaireDto(hero.getInventaire());

        // EquipementEquipe
        dto.equipementEquipe = toEquipementEquipeDto(hero.getEquipementEquipe());

        return dto;
    }

    private EquipementEquipeDto toEquipementEquipeDto(EquipementEquipe equipementEquipe) {
        EquipementEquipeDto dto = new EquipementEquipeDto();
        Arme arme = equipementEquipe.getArmeEquipee();
        dto.armeId = arme == null ? null : arme.type().name();
        dto.armures = new HashMap<>();
        equipementEquipe.getArmuresEquipees()
                .forEach((emplacementArmure, armure) ->
                        dto.armures.put(
                                emplacementArmure.name(),
                                armure == null ? null : armure.type().name()
                        ));
        return dto;
    }

    private InventaireDto toInventaireDto(Inventaire inventaire) {
        InventaireDto dto = new InventaireDto();
        dto.monnaie = inventaire.getMonnaie();

        dto.consommables = new HashMap<>();
        dto.equipements = new HashMap<>();

        inventaire.getConsommables().forEach((potion, value) -> dto.consommables.put(potion.type().name(), value));
        inventaire.getEquipements().forEach((equipement, value) -> dto.equipements.put(equipement.type().name(), value));

        return dto;
    }

    private StatistiquesDto toStatistiquesDto(Statistiques statistiques) {
        StatistiquesDto dto = new StatistiquesDto();
        dto.pointsVieMax = statistiques.pointsVieMax();
        dto.manaMax = statistiques.manaMax();
        dto.attaquePhysique = statistiques.attaquePhysique();
        dto.puissanceMagique = statistiques.puissanceMagique();
        dto.armure = statistiques.armure();
        dto.resistanceMagique = statistiques.resistanceMagique();
        dto.vitesse = statistiques.vitesse();
        return dto;
    }

    // --------------------
    // DTO -> DOMAIN (LOAD)
    // --------------------

    public Hero toDomain(SaveGameDto save) {
        if (save == null || save.hero == null) {
            throw new IllegalArgumentException("SaveGameDto vide ou invalide");
        }
        return toHeroDomain(save.hero);
    }

    private Hero toHeroDomain(HeroDto dto) {
        // Statistiques de base
        Statistiques statistiques = new Statistiques(
                dto.statistiques.pointsVieMax,
                dto.statistiques.manaMax,
                dto.statistiques.attaquePhysique,
                dto.statistiques.puissanceMagique,
                dto.statistiques.armure,
                dto.statistiques.resistanceMagique,
                dto.statistiques.vitesse
        );


        // Recreer le hero a partir du dto
        Hero hero = new Hero(dto.nom, dto.niveau, Archetype.valueOf(dto.archetype.name), dto.experience);
        hero.setStatistiques(statistiques);
        // PV/Mana actuels
        hero.setPointsVie(dto.pointsVie);
        hero.setMana(dto.mana);
        // Inventaire
        Inventaire inventaire = new Inventaire();
        inventaire.setMonnaie(dto.inventaire.monnaie);
        // Equipements depuis Forgeron.RATELIER
        if (dto.inventaire != null && dto.inventaire.equipements != null) {
            dto.inventaire.equipements.forEach((s, integer) -> {
                Armurerie armurerie = Armurerie.valueOf(s);
                int qte = integer;
                Equipement equipement = Forgeron.RATELIER.get(armurerie);
                if (equipement == null) {
                    throw new IllegalArgumentException("Equipement inconnu dans RATELIER : " + armurerie);
                }
                inventaire.ajouterEquipement(equipement, qte);
            });
        }
        // Consommables depuis Apothicaire.ETAGERE
        if (dto.inventaire != null && dto.inventaire.consommables != null) {
            for (Map.Entry<String, Integer> entry : dto.inventaire.consommables.entrySet()) {
                String s = entry.getKey();
                Integer integer = entry.getValue();
                if (s == null) continue;
                Potions potions = Potions.valueOf(s);
                int qte = integer;
                Potion potion = Apothicaire.ETAGERE.get(potions);
                if (potion == null) {
                    throw new IllegalArgumentException("Equipement inconnu dans ETAGERE : " + potions);
                }
                inventaire.ajouterConsommables(potion, qte);
            }
        }
        hero.setInventaire(inventaire);
        // Equipement equipe
        EquipementEquipe equipementEquipe = new EquipementEquipe();
        if (dto.equipementEquipe != null && dto.equipementEquipe.armeId != null) {
            // Arme
            Armurerie armurerie = Armurerie.valueOf(dto.equipementEquipe.armeId);
            Equipement equipement = Forgeron.RATELIER.get(armurerie);
            if (equipement == null) throw new IllegalArgumentException("Arme inconnue dans le RATELIER : " + armurerie);
            equipementEquipe.equiper(equipement);
        }
        if (dto.equipementEquipe != null && dto.equipementEquipe.armures != null) {
            // Armures (on ignore la clé emplacement, car armure connait deja son emplacement
            for (Map.Entry<String, String> entry : dto.equipementEquipe.armures.entrySet()) {
                String _ = entry.getKey();
                String value = entry.getValue();
                if (value == null) continue;
                Armurerie armureId = Armurerie.valueOf(value);
                Equipement equipement = Forgeron.RATELIER.get(armureId);
                if (equipement == null)
                    throw new IllegalArgumentException("Armure inconnue dans RATELIER : :" + armureId);
                equipementEquipe.equiper(equipement);
            }
        }
        hero.setEquipementEquipe(equipementEquipe);

        hero.clampRessources();

        return hero;
    }
}
