package rpg;

import boutique.Boutique;
import combat.Combat;
import consommables.Apothicaire;
import consommables.Consommable;
import consommables.Potions;
import core.Statistiques;
import donjon.Donjon;
import equipements.Armurerie;
import equipements.Equipement;
import equipements.Forgeron;
import personnages.Hero;
import personnages.Monstre;
import services.Service;

import java.util.HashMap;
import java.util.Map;

public class Rpg {
    private Hero hero;
    private Boutique boutique;

    public void play() {
        testData();
        menuPrincipal();
    }

    public void testData() {
        // TODO : Type de hero, caster/physical/merchant/assassin
        hero = new Hero("Jerry");
        boutique = new Boutique();
        Statistiques statsSupplementaires = new Statistiques(0,0,100,0,0,0,0);
        hero.setStatistiques(hero.getStatistiques().add(statsSupplementaires));
        hero.getInventaire().ajouterMonnaie(20000);

        Map<Equipement,Integer> nouveauxEquipements = new HashMap<>(){{
            put(Forgeron.RATELIER.get(Armurerie.EPEE_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.TETE_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.EPAULES_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.POIGNETS_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.TORSE_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.CEINTURE_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.MAINS_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.JAMBES_FER),1);
            put(Forgeron.RATELIER.get(Armurerie.PIEDS_FER),1);
        }};

        nouveauxEquipements.forEach((k,v)-> hero.getInventaire().ajouterEquipement(k,v));
        hero.equiperDepuisInventaire(Forgeron.RATELIER.get(Armurerie.EPEE_FER));

        Map<Consommable, Integer> mapConsommables = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Apothicaire.ETAGERE.get(Potions.PDV_MINEURE), 3),
                        Map.entry(Apothicaire.ETAGERE.get(Potions.PDV_SUPERIEURE), 3)
                )
        );
        hero.getInventaire().ajouterConsommables(mapConsommables);
    }

    public void menuPrincipal() {
        boolean choixMenu = false;
        while (!choixMenu) {
            // Boutique
            menuBoutique();
            // Achat & Revente
            // Selection de la difficulté
            // Combats
            Donjon donjon = new Donjon(10);
            IO.println("Vous entrez dans un donjon !");
            IO.println("===== DONJON =====");

            for (Monstre monstre : donjon.getMonstres()) {
                Combat combat = new Combat(hero, monstre);
                boolean quitterDonjon = combat.debutCombat();
                if (quitterDonjon) {
                    break;
                }
                if (hero.getPointsVie() <= 0) {
                    IO.println("Vous êtes mort.");
                    break;
                } else if (donjon.getMonstres().getLast() != monstre) {
                    IO.println("Vous entrez dans la salle suivante ...");
                } else {
                    IO.println("Vous êtes sorti du donjon sain et sauf.");
                    choixMenu = true;
                }
            }
            // Quitter
        }
    }

    public void menuEquipement(){
        
    }

    public void menuBoutique() {
        IO.println("===== BOUTIQUE =====");
        boolean choixMenuBoutique = false;
        while (!choixMenuBoutique) {
            char choixUtilisateur = getChoixMenuBoutique();
            switch (choixUtilisateur) {
                case '1' -> menuConsommables();
                case '2' -> menuEquipements();
                case '3' -> choixMenuBoutique = true;
            }
        }
    }

    private char getChoixMenuBoutique() {
        IO.println("1 - Consommables");
        IO.println("2 - Equipements");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    public void menuConsommables() {
        boolean choixMenuConsommables = false;
        while (!choixMenuConsommables) {
            String choixUtilisateur = getChoixMenuConsommables();
            if (choixUtilisateur.equalsIgnoreCase("retour"))
                return;
            if (Potions.contains(choixUtilisateur)) {
                if (boutique.getConsommables().get(Apothicaire.ETAGERE.get(Potions.getByName(choixUtilisateur))) <= 0) {
                    IO.println(String.format("L'apothicaire n'a plus de %s en stock ...", choixUtilisateur));
                } else {
                    Consommable consommableChoisi = Apothicaire.ETAGERE.get(Potions.getByName(choixUtilisateur));
                    if (hero.getInventaire().getMonnaie() < consommableChoisi.prixAchat()) {
                        IO.println(String.format("Vous n'avez pas assez d'argent pour acheter %s !", choixUtilisateur));
                    } else {
                        if (boutique.retirerConsommables(consommableChoisi, 1)) {
                            hero.getInventaire().ajouterConsommables(consommableChoisi, 1);
                            hero.getInventaire().retirerMonnaie(consommableChoisi.prixAchat());
                            IO.println(String.format("Vous avez acheté %s pour %s.", choixUtilisateur, Service.formatMonnaie(consommableChoisi.prixAchat())));
                            IO.println(String.format("Il vous reste %s.", Service.formatMonnaie(hero.getInventaire().getMonnaie())));
                            choixMenuConsommables = true;
                        } else {
                            IO.println(String.format("L'apothicaire n'a plus assez de %s en stock ...", choixUtilisateur));
                        }
                    }

                }
            } else {
                IO.println("L'apothicaire ne possède pas cet objet...");
            }
        }

    }

    private String getChoixMenuConsommables() {
        IO.println(String.format("Votre argent : %s", Service.formatMonnaie(hero.getInventaire().getMonnaie())));
        boutique.getConsommables().forEach((k, v) -> {
            if (v > 0) {
                IO.println(String.format("%s - Stock : %d, Prix : %s", k.nom(), v, Service.formatMonnaie(k.prixAchat())));
            }
        });
        IO.println("Retour");
        return IO.readln();
    }

    public void menuEquipements() {
        boolean choixMenuEquipements = false;
        while (!choixMenuEquipements) {
            String choixUtilisateur = getChoixMenuEquipements();
            if (choixUtilisateur.equalsIgnoreCase("retour"))
                return;
            if (Armurerie.contains(choixUtilisateur)) {
                if (boutique.getEquipements().get(Forgeron.RATELIER.get(Armurerie.getByName(choixUtilisateur))) <= 0) {
                    IO.println(String.format("Le forgeron n'a plus de %s en stock ...", choixUtilisateur));
                } else {
                    Equipement equipementChoisi = Forgeron.RATELIER.get(Armurerie.getByName(choixUtilisateur));
                    if (hero.getInventaire().getMonnaie() < equipementChoisi.prixAchat()) {
                        IO.println(String.format("Vous n'avez pas assez d'argent pour acheter %s !", choixUtilisateur));
                    } else {
                        if (boutique.retirerEquipements(equipementChoisi, 1)) {
                            hero.getInventaire().ajouterEquipement(equipementChoisi, 1);
                            hero.getInventaire().retirerMonnaie(equipementChoisi.prixAchat());
                            IO.println(String.format("Vous avez acheté %s pour %s.", choixUtilisateur, Service.formatMonnaie(equipementChoisi.prixAchat())));
                            IO.println(String.format("Il vous reste %s.", Service.formatMonnaie(hero.getInventaire().getMonnaie())));
                            choixMenuEquipements = true;
                        } else {
                            IO.println(String.format("Le forgeron n'a plus assez de %s en stock ...", choixUtilisateur));
                        }
                    }
                }
            } else {
                IO.println("Le forgeron ne possède pas cet objet...");
            }
        }

    }

    private String getChoixMenuEquipements() {
        IO.println(String.format("Votre argent : %s", Service.formatMonnaie(hero.getInventaire().getMonnaie())));
        boutique.getEquipements().forEach((k, v) -> {
            if (v > 0) {
                IO.println(String.format("%s - Stock : %d, Prix : %s", k.nom(), v, Service.formatMonnaie(k.prixAchat())));
            }
        });
        IO.println("Retour");
        return IO.readln();
    }
}
