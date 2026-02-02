package persistance.mapper;

import consommables.ApothicaireStandard;
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
        return new SaveGameDto(toHeroDto(hero));
    }

    private HeroDto toHeroDto(Hero hero) {
        return new HeroDto(
                hero.getNom(),
                hero.getExperience(),
                hero.getNiveau(),
                hero.getPointsVie(),
                hero.getMana(),
                toInventaireDto(hero.getInventaire()),
                toEquipementEquipeDto(hero.getEquipementEquipe()),
                toStatistiquesDto(hero.getStatistiques()),
                new ArchetypeDto(hero.getArchetype().name())
        );
    }

    private EquipementEquipeDto toEquipementEquipeDto(EquipementEquipe equipementEquipe) {
        Arme arme = equipementEquipe.getArmeEquipee();
        String nomArme = arme == null ? null : arme.type().name();
        Map<String, String> armures = new HashMap<>();
        equipementEquipe.getArmuresEquipees()
                .forEach((emplacementArmure, armure) ->
                        armures.put(
                                emplacementArmure.name(),
                                armure == null ? null : armure.type().name()
                        ));
        return new EquipementEquipeDto(nomArme, armures);
    }

    private InventaireDto toInventaireDto(Inventaire inventaire) {
        Map<String, Integer> consommables = new HashMap<>();
        Map<String, Integer> equipements = new HashMap<>();

        inventaire.getConsommables().forEach((potion, value) -> consommables.put(potion.type().name(), value));
        inventaire.getEquipements().forEach((equipement, value) -> equipements.put(equipement.type().name(), value));

        return new InventaireDto(inventaire.getMonnaie(), consommables, equipements);
    }

    private StatistiquesDto toStatistiquesDto(Statistiques statistiques) {
        return new StatistiquesDto(
                statistiques.pointsVieMax(),
                statistiques.manaMax(),
                statistiques.attaquePhysique(),
                statistiques.puissanceMagique(),
                statistiques.armure(),
                statistiques.resistanceMagique(),
                statistiques.vitesse()
                );
    }

    // --------------------
    // DTO -> DOMAIN (LOAD)
    // --------------------

    public Hero toDomain(SaveGameDto save) {
        if (save == null || save.hero() == null) {
            throw new IllegalArgumentException("SaveGameDto vide ou invalide");
        }
        return toHeroDomain(save.hero());
    }

    private Hero toHeroDomain(HeroDto dto) {
        Hero hero = getHeroStatistiquesDomain(dto);
        toHeroInventaireDomain(dto, hero);
        toHeroEquipementEquipeDomain(dto, hero);
        hero.clampRessources();
        return hero;
    }

    private static void toHeroEquipementEquipeDomain(HeroDto dto, Hero hero) {
        // Equipement equipe
        EquipementEquipe equipementEquipe = new EquipementEquipe();
        if (dto.equipementEquipe() != null && dto.equipementEquipe().armeId() != null) {
            // Arme
            Armurerie armurerie = Armurerie.valueOf(dto.equipementEquipe().armeId());
            ForgeronStandard forgeronStandard = new ForgeronStandard();
            Equipement equipement = forgeronStandard.forger(armurerie);
            if (equipement == null) throw new IllegalArgumentException("Arme inconnue dans le RATELIER : " + armurerie);
            equipementEquipe.equiper(equipement);
        }
        if (dto.equipementEquipe() != null && dto.equipementEquipe().armures() != null) {
            // Armures (on ignore la clé emplacement, car armure connait deja son emplacement
            for (Map.Entry<String, String> entry : dto.equipementEquipe().armures().entrySet()) {
                String _ = entry.getKey();
                String value = entry.getValue();
                if (value == null) continue;
                Armurerie armureId = Armurerie.valueOf(value);
                ForgeronStandard forgeronStandard = new ForgeronStandard();
                Equipement equipement = forgeronStandard.forger(armureId);
                if (equipement == null)
                    throw new IllegalArgumentException("Armure inconnue dans RATELIER : :" + armureId);
                equipementEquipe.equiper(equipement);
            }
        }
        hero.setEquipementEquipe(equipementEquipe);
    }

    private static Hero getHeroStatistiquesDomain(HeroDto dto) {
        // Statistiques de base
        Statistiques statistiques = new Statistiques(
                dto.statistiques().pointsVieMax(),
                dto.statistiques().manaMax(),
                dto.statistiques().attaquePhysique(),
                dto.statistiques().puissanceMagique(),
                dto.statistiques().armure(),
                dto.statistiques().resistanceMagique(),
                dto.statistiques().vitesse()
        );


        // Recreer le hero a partir du dto
        Hero hero = new Hero(dto.nom(), dto.niveau(), Archetype.valueOf(dto.archetype().name()), dto.experience());
        hero.setStatistiques(statistiques);
        // PV/Mana actuels
        hero.setPointsVie(dto.pointsVie());
        hero.setMana(dto.mana());
        return hero;
    }

    private static void toHeroInventaireDomain(HeroDto dto, Hero hero) {
        // Inventaire
        Inventaire inventaire = new Inventaire();
        inventaire.setMonnaie(dto.inventaire().monnaie());
        // Equipements depuis Forgeron.RATELIER
        if (dto.inventaire().equipements() != null) {
            dto.inventaire().equipements().forEach((s, integer) -> {
                Armurerie armurerie = Armurerie.valueOf(s);
                int qte = integer;
                ForgeronStandard forgeronStandard = new ForgeronStandard();
                Equipement equipement = forgeronStandard.forger(armurerie);
                if (equipement == null) {
                    throw new IllegalArgumentException("Equipement inconnu dans RATELIER : " + armurerie);
                }
                inventaire.ajouterEquipement(equipement, qte);
            });
        }
        // Consommables depuis Apothicaire.ETAGERE
        if (dto.inventaire().consommables() != null) {
            for (Map.Entry<String, Integer> entry : dto.inventaire().consommables().entrySet()) {
                String s = entry.getKey();
                Integer integer = entry.getValue();
                if (s == null) continue;
                Potions potions = Potions.valueOf(s);
                int qte = integer;
                ApothicaireStandard apothicaireStandard = new ApothicaireStandard();
                Potion potion = apothicaireStandard.melanger(potions);
                if (potion == null) {
                    throw new IllegalArgumentException("Equipement inconnu dans ETAGERE : " + potions);
                }
                inventaire.ajouterConsommables(potion, qte);
            }
        }
        hero.setInventaire(inventaire);
    }
}
