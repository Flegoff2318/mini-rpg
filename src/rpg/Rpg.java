package rpg;

import boutique.Boutique;
import combat.Combat;
import consommables.Apothicaire;
import consommables.Consommable;
import consommables.Potions;
import core.Statistiques;
import donjon.Donjon;
import equipements.*;
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
        Statistiques statsSupplementaires = new Statistiques(0, 0, 100, 0, 0, 0, 0);
        hero.setStatistiques(hero.getStatistiques().add(statsSupplementaires));
        hero.getInventaire().ajouterMonnaie(20000);

        Map<Equipement, Integer> nouveauxEquipements = new HashMap<>() {{
            put(Forgeron.RATELIER.get(Armurerie.EPEE_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.TETE_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.EPAULES_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.POIGNETS_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.TORSE_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.CEINTURE_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.MAINS_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.JAMBES_FER), 1);
            put(Forgeron.RATELIER.get(Armurerie.PIEDS_FER), 1);
        }};

        nouveauxEquipements.forEach((k, v) -> hero.getInventaire().ajouterEquipement(k, v));
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
        boolean partieEnCours = true;
        while (partieEnCours) {
            IO.println("===== MENU PRINCIPAL =====");
            boolean choixMenuPrincipal = false;
            while (!choixMenuPrincipal) {
                char choixUtilisateur = getChoixMenuPrincipal();
                switch (choixUtilisateur) {
                    case '1' -> menuBoutique();
                    case '2' -> menuInventaire();
                    case '3' -> {
                        boolean joueurEnVie = aventure();
                        if (!joueurEnVie) {
                            choixMenuPrincipal = true;
                            partieEnCours = false;
                        }
                    }
                    case '4' -> {
                        IO.println("PeepoSad :(");
                        choixMenuPrincipal = true;
                        partieEnCours = false;
                    }
                }

            }
            // TODO : Achat & Revente
            // TODO : Selection de la difficulté
            // Quitter
        }
    }

    private boolean aventure() {
        Donjon donjon = new Donjon(10);
        IO.println("===== DONJON =====");
        boolean joueurEnVie = true;
        for (Monstre monstre : donjon.getMonstres()) {
            Combat combat = new Combat(hero, monstre);
            boolean quitterDonjon = combat.debutCombat();
            if (quitterDonjon) {
                IO.println("Vous rebroussez chemin.");
                break;
            }
            if (hero.getPointsVie() <= 0) {
                IO.println("Vous êtes mort.");
                joueurEnVie = false;
            } else if (donjon.getMonstres().getLast() != monstre) {
                IO.println("Vous entrez dans la salle suivante ...");
            } else {
                IO.println("Vous êtes sorti du donjon sain et sauf.");
                break;
            }
        }
        return joueurEnVie;
    }

    private void menuInventaire() {
        IO.println("===== INVENTAIRE =====");
        boolean choixMenuInventaire = false;
        while (!choixMenuInventaire) {
            char choixUtilisateur = getChoixMenuInventaire();
            switch (choixUtilisateur) {
                case '1' -> inventaire();
                case '2' -> consommables();
                case '3' -> choixMenuInventaire = true;
            }
        }
    }

    private void consommables() {
        IO.println("===== CONSOMMABLES =====");
        hero.getInventaire().getConsommables().forEach((k, v) -> IO.println(String.format("%s - Stock : %s", k.nom(), v)));
    }

    private void inventaire() {
        IO.println("===== SAC A DOS =====");
        hero.getInventaire().getEquipements().forEach((k, _) -> {
            switch (k) {
                case Arme arme -> IO.println(arme);
                case Armure armure -> IO.println(armure);
            }
        });
        IO.println("===== EQUIPEMENT PORTE =====");
        IO.println("====° Arme °====");
        if (hero.getEquipementEquipe().getArmeEquipee() != null) {
            IO.println(hero.getEquipementEquipe().getArmeEquipee());
        } else {
            IO.println("Aucune arme équipée.");
        }
        IO.println("====° Armures °====");
        if (hero.getEquipementEquipe().getArmuresEquipees().isEmpty()) {
            IO.println("Aucune armure équipée.");
        } else {
            hero.getEquipementEquipe().getArmuresEquipees().forEach((_, v) -> IO.println(v));
        }
    }

    public char getChoixMenuInventaire() {
        IO.println("1 - Equipement");
        IO.println("2 - Consommables");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    public char getChoixMenuPrincipal() {
        IO.println("1 - Boutique");
        IO.println("2 - Equipements");
        IO.println("3 - A l'aventure !");
        IO.println("4 - Quitter");
        return IO.readln().charAt(0);
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
        IO.println("1 - Apothicaire");
        IO.println("2 - Forgeron");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    public void menuConsommables() {
        IO.println("===== APOTHICAIRE =====");
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
        IO.println("===== FORGERON =====");
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
