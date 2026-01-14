package rpg;

import boutique.Boutique;
import combat.Combat;
import consommables.Apothicaire;
import consommables.Consommable;
import consommables.ContexteConsommable;
import consommables.Potions;
import core.Statistiques;
import donjon.Difficulte;
import donjon.Donjon;
import equipements.Armurerie;
import equipements.Equipement;
import equipements.Forgeron;
import personnages.Archetype;
import personnages.Hero;
import personnages.Monstre;
import services.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Rpg {
    private Hero hero;
    private Boutique boutique;
    private final ContexteConsommable contexteConsommable;
    private Difficulte difficulte = Difficulte.FACILE;
    private int nombreMonstresDonjon = 10;

    public Rpg() {
        this.contexteConsommable = new ContexteConsommable();
    }

    public void play() {
        testData();
        menuPrincipal();
    }

    public void testData() {
        hero = new Hero("Jerry", Archetype.ASSASSIN);
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
            boolean choixMenuPrincipal = false;
            while (!choixMenuPrincipal) {
                IO.println("===== MENU PRINCIPAL =====");
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
                    case '4' -> menuOptions();
                    case '5' -> {
                        IO.println("Vous êtes touché au genou par une flèche, votre aventure s'achève...");
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

    private void menuOptions() {
        IO.println("===== OPTIONS =====");
        boolean choixMenuOptions = false;
        while (!choixMenuOptions) {
            char choixUtilisateur = getChoixMenuOptions();
            switch (choixUtilisateur) {
                case '1' -> getChoixMenuNombreMonstresDonjon();
                case '2' -> changerDifficulte();
                case '3' -> choixMenuOptions = true;
            }
        }
    }

    private void changerDifficulte() {
        boolean choixMenuDifficulte = false;
        while (!choixMenuDifficulte) {
            char choixUtilisateur = getChoixDifficulte();
            switch (choixUtilisateur) {
                case '1' -> {
                    difficulte = Difficulte.FACILE;
                    IO.println(String.format("Nouvelle difficulte : %s", difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '2' -> {
                    difficulte = Difficulte.MOYEN;
                    IO.println(String.format("Nouvelle difficulte : %s", difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '3' -> {
                    difficulte = Difficulte.DIFFICILE;
                    IO.println(String.format("Nouvelle difficulte : %s", difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '4' -> {
                    difficulte = Difficulte.MORTEL;
                    IO.println(String.format("Nouvelle difficulte : %s", difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '5' -> choixMenuDifficulte = true;
            }
        }
    }

    private char getChoixDifficulte() {
        IO.println(String.format("Difficulte actuelle : %s", difficulte.getLabel()));
        IO.println(String.format("1 - %s", Difficulte.FACILE.getLabel()));
        IO.println(String.format("2 - %s", Difficulte.MOYEN.getLabel()));
        IO.println(String.format("3 - %s", Difficulte.DIFFICILE.getLabel()));
        IO.println(String.format("4 - %s", Difficulte.MORTEL.getLabel()));
        IO.println("5 - Retour");
        return IO.readln().charAt(0);
    }

    private void getChoixMenuNombreMonstresDonjon() {
        IO.println(String.format("Nombre actuel de monstres par donjon : %d", nombreMonstresDonjon));
        IO.print("Entre le nouveau nombre de monstres souhaité (ou `retour`): ");
        String saisieUtilisateur = IO.readln();
        if (saisieUtilisateur.equalsIgnoreCase("retour"))
            return;
        boolean saisieValide = false;
        while (!saisieValide) {
            try {
                nombreMonstresDonjon = Integer.parseInt(saisieUtilisateur);
                IO.println(String.format("Nouveau nombre de monstres dans le donjon : %d", nombreMonstresDonjon));
                saisieValide = true;
            } catch (NumberFormatException _) {
            }
        }
    }

    private char getChoixMenuOptions() {
        IO.println("1 - Nombre de monstres par donjon");
        IO.println("2 - Difficulte du donjon");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    private boolean aventure() {
        Donjon donjon = new Donjon(nombreMonstresDonjon, hero.getNiveau(), difficulte);
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
        boolean choixMenuConsommables = false;
        while (!choixMenuConsommables) {
            String choixUtilisateur = getChoixMenuConsommablesUtilise();
            if (choixUtilisateur.equals("retour")) {
                return;
            } else {
                Consommable consommable = hero.getInventaire().getConsommables().keySet().stream()
                        .filter(c -> c.nom().equalsIgnoreCase(choixUtilisateur))
                        .findFirst()
                        .orElse(null);
                if (consommable == null) {
                    IO.println("Ce consommable n'existe pas.");
                } else {
                    // Le hero va s'infliger des dégats si il essaye de boire une potion de type degats, c'est pas très malin de sa part!
                    choixMenuConsommables = contexteConsommable.utiliserConsommable(hero, hero, consommable);
                }
            }

        }

    }

    private String getChoixMenuConsommablesUtilise() {
        hero.getInventaire().getConsommables().forEach((k, v) -> IO.println(String.format("%s - Stock : %s", k.nom(), v)));
        IO.println("retour");
        return IO.readln();
    }

    private void inventaire() {
        IO.println("===== BARDA =====");
        boolean choixMenuSacADos = false;
        while (!choixMenuSacADos) {
            char choixUtilisateur = getChoixSacADos();
            switch (choixUtilisateur) {
                case '1' -> {
                    if (afficherSacADos()) {
                        choixEquipementAEquiper();
                    }
                }
                case '2' -> {
                    if (afficherEquipementPorte()) {
                        choixEquipementADesequiper();
                    }
                }
                case '3' -> choixMenuSacADos = true;
            }
        }

    }

    private void choixEquipementADesequiper() {
        IO.println("1 - Desequiper l'arme");
        IO.println("2 - Desequiper une piece d'armure");
        IO.println("3 - Retour");
        boolean choixMenuDesequiper = false;
        while (!choixMenuDesequiper) {
            IO.print("Entre le nom de l'equipement : ");
            char choixMenu = IO.readln().charAt(0);
            switch (choixMenu) {
                case '1' -> hero.desequiperDepuisInventaire(hero.getEquipementEquipe().getArmeEquipee());
                case '2' -> {
                    String choixUtilisateur = IO.readln();
                    hero.getEquipementEquipe().getArmuresEquipees().values().stream()
                            .filter(equipement -> equipement.nom().equalsIgnoreCase(choixUtilisateur))
                            .findFirst()
                            .ifPresent(equipement -> hero.getInventaire().ajouterEquipement(hero.getEquipementEquipe().desequiperArmure(equipement.emplacementArmure()), 1));
                }
                case '3' -> choixMenuDesequiper = true;
            }
        }
    }

    private boolean afficherEquipementPorte() {
        boolean arme = false;
        boolean armures = false;

        IO.println("===== EQUIPEMENT PORTE =====");
        IO.println("====° Arme °====");
        if (hero.getEquipementEquipe().getArmeEquipee() != null) {
            IO.println(hero.getEquipementEquipe().getArmeEquipee());
            arme = true;
        } else {
            IO.println("Aucune arme équipée.");
        }
        IO.println("====° Armures °====");
        if (hero.getEquipementEquipe().getArmuresEquipees().isEmpty()) {
            IO.println("Aucune armure équipée.");
        } else {
            hero.getEquipementEquipe().getArmuresEquipees().values().stream()
                    .sorted(Comparator.comparingInt(Equipement::niveauRequis))
                    .forEach(IO::println);
            armures = true;
        }
        return arme || armures;
    }

    private void choixEquipementAEquiper() {
        IO.print("Entre le nom de l'equipement : ");
        String choixUtilisateur = IO.readln();
        hero.getInventaire().getEquipements().keySet().stream()
                .filter(equipement -> equipement.nom().equalsIgnoreCase(choixUtilisateur))
                .findFirst()
                .ifPresent(equipement -> hero.equiperDepuisInventaire(equipement));
    }

    private boolean afficherSacADos() {
        IO.println("===== SAC A DOS =====");
        if (hero.getInventaire().getEquipements().isEmpty()) {
            return false;
        }
        hero.getInventaire().getEquipements().keySet().stream()
                .sorted(Comparator.comparingInt(Equipement::niveauRequis))
                .forEach(IO::println);

        return true;
    }

    private char getChoixSacADos() {
        IO.println("1 - Equiper une piece d'equipement");
        IO.println("2 - Desequiper une piece d'equipement");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    public char getChoixMenuInventaire() {
        IO.println("1 - Barda");
        IO.println("2 - Consommables");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    public char getChoixMenuPrincipal() {
        IO.println("1 - Boutique");
        IO.println("2 - Inventaire");
        IO.println("3 - A l'aventure !");
        IO.println("4 - Options");
        IO.println("5 - Quitter");
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
