package rpg;

import boutique.Boutique;
import combat.Combat;
import consommables.ApothicaireStandard;
import consommables.ContexteConsommable;
import consommables.Potion;
import consommables.Potions;
import donjon.Difficulte;
import donjon.Donjon;
import equipements.Armure;
import equipements.Armurerie;
import equipements.Equipement;
import equipements.ForgeronStandard;
import inventaire.entry.ItemEntry;
import inventaire.helpers.InventaireViewBuilder;
import persistance.SaveManager;
import persistance.SaveService;
import persistance.dto.SaveGameDto;
import persistance.mapper.SaveGameMapper;
import personnages.Archetype;
import personnages.Hero;
import personnages.Monstre;
import services.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Rpg {
    private static final String SAVE_FILE_FORMAT = "%s - %s";
    private static final String INVALID_SELECTION_MESSAGE = "Ce choix n'est pas dans la liste !";
    private static final String DIFFICULTY_MESSAGE_FORMAT = "Nouvelle difficulte : %s";
    private static final String RETOUR = "retour";
    private Hero hero;
    private Boutique boutique;
    private final ContexteConsommable contexteConsommable;
    private Difficulte difficulte = Difficulte.FACILE;
    private int nombreMonstresDonjon = 10;
    private final ForgeronStandard forgeronStandard = new ForgeronStandard();
    private final ApothicaireStandard apothicaireStandard = new ApothicaireStandard();

    Path savesDir = Path.of("saves");
    private final SaveService saveService = new SaveService();
    private final SaveGameMapper saveGameMapper = new SaveGameMapper();
    private final SaveManager saveManager = new SaveManager(savesDir, saveService);

    public Rpg() {
        this.contexteConsommable = new ContexteConsommable();
    }

    public void play() {
        if (newGameOrContinue()) {
            menuPrincipal();
        }
    }

    private boolean startMenu() {
        this.boutique = new Boutique();
        return newGame();
    }

    private boolean newGameOrContinue() {
        boolean canContinueGame;
        try {
            canContinueGame = saveManager.loadLast().isPresent();
        } catch (IOException _) {
            canContinueGame = false;
        }
        String choixUtilisateur;
        boolean choixValide = false;
        if (canContinueGame) {
            while (!choixValide) {
                IO.println("1 - Continuer");
                IO.println("2 - Charger");
                IO.println("3 - Nouvelle partie");
                IO.println("4 - Quitter");
                choixUtilisateur = IO.readln();
                switch (choixUtilisateur) {
                    case "1" -> choixValide = continueGame();
                    case "2" -> choixValide = loadGameFromList();
                    case "3" -> choixValide = startMenu();
                    case "4" -> {
                        return false;
                    }
                    default -> IO.println(INVALID_SELECTION_MESSAGE);
                }
            }

        } else {
            while (!choixValide) {
                IO.println("1 - Nouvelle partie");
                IO.println("2 - Charger");
                IO.println("3 - Quitter");
                choixUtilisateur = IO.readln();
                switch (choixUtilisateur) {
                    case "1" -> choixValide = startMenu();
                    case "2" -> choixValide = loadGameFromList();
                    case "3" -> {
                        return false;
                    }
                    default -> IO.println(INVALID_SELECTION_MESSAGE);
                }
            }

        }
        return true;
    }

    private void quickSave() {
        SaveGameDto dto = saveGameMapper.toDto(hero);
        try {
            saveManager.saveOverwrite(hero.getNom(), dto);
        } catch (IOException _) {
            IO.println("Erreur lors de la sauvegarde rapide.");
        }
    }

    private boolean saveNewGame() {
        SaveGameDto dto = saveGameMapper.toDto(hero);
        boolean saved = false;
        try {
            saveManager.saveNew(hero.getNom(), dto);
            saved = true;
        } catch (IOException _) {
            IO.println("Ce nom est deja pris !");
        }
        return saved;
    }

    private boolean continueGame() {
        try {
            Optional<SaveGameDto> optionalSaveGameDto = saveManager.loadLast();
            if (optionalSaveGameDto.isEmpty()) {
                IO.println("Aucune partie recente.");
                return false;
            }
            SaveGameDto dto = optionalSaveGameDto.get();
            hero = saveGameMapper.toDomain(dto);
            IO.println("Partie chargee !");
            return true;
        } catch (IOException _) {
            IO.println("Impossible de charger la derniere partie.");
            return false;
        }
    }

    private boolean loadGameFromList() {
        try {
            List<String> saveFiles = saveManager.listSaveFiles();
            int listSize = saveFiles.size();
            for (int i = 0; i < listSize; i++) {
                IO.println(String.format(SAVE_FILE_FORMAT, i + 1, saveFiles.get(i).replaceAll(".json", "")));
            }
            IO.println("0 - Annuler");
            IO.print("Sauvegarde : ");

            String choixSauvegardeUtilisateur = IO.readln();
            int indexSauvegarde = Integer.parseInt(choixSauvegardeUtilisateur);

            if (indexSauvegarde == 0)
                return false;

            String fileName = saveFiles.get(indexSauvegarde - 1);
            SaveGameDto dto = saveManager.loadByFileName(fileName);
            hero = saveGameMapper.toDomain(dto);
            IO.println("Partie chargee !");

            return true;
        } catch (NumberFormatException _) {
            IO.println("Ce n'est pas un nombre !");
            return false;
        } catch (IOException _) {
            IO.println("Erreur lors du chargement.");
            return false;
        }
    }

    private boolean newGame() {
        boolean creationPersonnage = false;
        IO.println("===== CREATION PERSONNAGE =====");
        IO.println("Choix de l'archétype : ");
        afficherArchetypesDisponibles();
        while (!creationPersonnage) {
            Archetype archetypeHero = selectionnerArchetype();
            if (archetypeHero != null) {
                boolean choixNom = false;
                String nom;
                while (!choixNom) {
                    nom = IO.readln("Choisissez votre nom : ");
                    if (!nom.isBlank() && saveNewGame()) {
                        hero = new Hero(nom, archetypeHero);
                        choixNom = true;
                        creationPersonnage = true;
                    }
                }
            }
        }
        return true;
    }

    private static Archetype selectionnerArchetype() {
        Archetype archetypeHero = null;
        String entreeUtilisateur = IO.readln();
        char choixArchetype = entreeUtilisateur.isBlank() ? 'x' : entreeUtilisateur.charAt(0);
        switch (choixArchetype) {
            case '1' -> archetypeHero = Archetype.MAGE;
            case '2' -> archetypeHero = Archetype.ASSASSIN;
            case '3' -> archetypeHero = Archetype.GUERRIER;
            default -> IO.println(INVALID_SELECTION_MESSAGE);
        }
        return archetypeHero;
    }

    private static void afficherArchetypesDisponibles() {
        int count = 1;
        for (Archetype archetype : Archetype.values()) {
            IO.println(String.format("%d - %s", count, archetype.getLabel()));
            count++;
        }
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
                        quickSave();
                    }
                    case '4' -> menuOptions();
                    case '5' -> {
                        quickSave();
                        IO.println("Vous êtes touché au genou par une flèche, votre aventure s'achève...");
                        choixMenuPrincipal = true;
                        partieEnCours = false;
                    }
                    default -> IO.println(INVALID_SELECTION_MESSAGE);
                }
            }
        }
    }

    private void menuOptions() {
        IO.println("===== REGLAGE DIFFICULTE =====");
        boolean choixMenuOptions = false;
        while (!choixMenuOptions) {
            char choixUtilisateur = getChoixMenuOptions();
            switch (choixUtilisateur) {
                case '1' -> getChoixMenuNombreMonstresDonjon();
                case '2' -> changerDifficulte();
                case '3' -> choixMenuOptions = true;
                default -> IO.println(INVALID_SELECTION_MESSAGE);
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
                    IO.println(String.format(DIFFICULTY_MESSAGE_FORMAT, difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '2' -> {
                    difficulte = Difficulte.MOYEN;
                    IO.println(String.format(DIFFICULTY_MESSAGE_FORMAT, difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '3' -> {
                    difficulte = Difficulte.DIFFICILE;
                    IO.println(String.format(DIFFICULTY_MESSAGE_FORMAT, difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '4' -> {
                    difficulte = Difficulte.MORTEL;
                    IO.println(String.format(DIFFICULTY_MESSAGE_FORMAT, difficulte.getLabel()));
                    choixMenuDifficulte = true;
                }
                case '5' -> choixMenuDifficulte = true;
                default -> IO.println(INVALID_SELECTION_MESSAGE);
            }
        }
        quickSave();
    }

    private char getChoixDifficulte() {
        IO.println(String.format("Difficulte actuelle : %s", difficulte.getLabel()));
        IO.println(String.format("1 - %s", Difficulte.FACILE.getLabel()));
        IO.println(String.format("2 - %s", Difficulte.MOYEN.getLabel()));
        IO.println(String.format("3 - %s", Difficulte.DIFFICILE.getLabel()));
        IO.println(String.format("4 - %s", Difficulte.MORTEL.getLabel()));
        IO.println("5 - " + RETOUR);
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
    }

    private void getChoixMenuNombreMonstresDonjon() {
        IO.println(String.format("Nombre actuel de monstres par donjon : %d", nombreMonstresDonjon));
        IO.print("Entre le nouveau nombre de monstres souhaité (ou `" + RETOUR + "`): ");
        String saisieUtilisateur = IO.readln();
        if (saisieUtilisateur.equalsIgnoreCase(RETOUR))
            return;
        boolean saisieValide = false;
        while (!saisieValide) {
            try {
                nombreMonstresDonjon = Integer.parseInt(saisieUtilisateur);
                IO.println(String.format("Nouveau nombre de monstres dans le donjon : %d", nombreMonstresDonjon));
                saisieValide = true;
            } catch (NumberFormatException _) {
                IO.println("Ce n'est pas un nombre !");
            }
        }
        quickSave();
    }

    private char getChoixMenuOptions() {
        IO.println("1 - Nombre de monstres par donjon");
        IO.println("2 - Difficulte du donjon");
        IO.println("3 - " + RETOUR);
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
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
            } else {
                if (hero.getPointsVie() <= 0) {
                    IO.println("Vous êtes mort.");
                    joueurEnVie = false;
                } else if (donjon.getMonstres().getLast() != monstre) {
                    IO.println("Vous entrez dans la salle suivante ...");
                } else {
                    IO.println("Vous êtes sorti du donjon sain et sauf.");
                    quitterDonjon = true;
                }
            }
            if (quitterDonjon)
                break;
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
                default -> IO.println(INVALID_SELECTION_MESSAGE);
            }
        }
        quickSave();
    }

    private void consommables() {
        IO.println("===== CONSOMMABLES =====");
        boolean choixMenuConsommables = false;
        while (!choixMenuConsommables) {
            String choixUtilisateur = getChoixMenuConsommablesUtilise();
            if (choixUtilisateur.equals(RETOUR)) {
                return;
            } else {
                Potion potion = hero.getInventaire().getConsommables().keySet().stream()
                        .filter(c -> c.nom().equalsIgnoreCase(choixUtilisateur))
                        .findFirst()
                        .orElse(null);
                if (potion == null) {
                    IO.println("Ce consommable n'existe pas.");
                } else {
                    // Le hero va s'infliger des dégats si il essaye de boire une potion de type degats, c'est pas très malin de sa part!
                    choixMenuConsommables = contexteConsommable.utiliserConsommable(hero, hero, potion);
                }
            }

        }
        quickSave();
    }

    private String getChoixMenuConsommablesUtilise() {
        hero.getInventaire().getConsommables().forEach((k, v) -> IO.println(String.format("%s - Stock : %s", k.nom(), v)));
        IO.println(RETOUR);
        return IO.readln();
    }

    private void inventaire() {
        IO.println("===== BARDA =====");
        boolean choixMenuSacADos = false;
        while (!choixMenuSacADos) {
            char choixUtilisateur = getChoixSacADos();
            switch (choixUtilisateur) {
                case '1' -> afficherSacADos();
                case '2' -> afficherEquipementPorte();
                case '3' -> choixMenuSacADos = true;
                default -> IO.println(INVALID_SELECTION_MESSAGE);
            }
        }
        quickSave();
    }

    private void afficherEquipementPorte() {
        IO.println("===== EQUIPEMENT PORTE =====");
        boolean isArmeEquipee = afficherArmeEquipee();
        IO.println("====° Armures °====");
        boolean isArmuresEquipees = afficherArmuresEquipees(isArmeEquipee);
        if (isArmeEquipee || isArmuresEquipees) {
            IO.println("O - Retour");
            boolean choixValide = false;
            List<Armure> armuresEquipeesView = InventaireViewBuilder.buildEquipementsArmureView(hero.getEquipementEquipe());
            while (!choixValide) {
                choixValide = choixRetirerEquipement(false, isArmeEquipee, armuresEquipeesView);
            }
        }
    }

    private boolean choixRetirerEquipement(boolean choixValide, boolean arme, List<Armure> armuresEquipeesView) {
        String choixUtilisateur = IO.readln("Objet a desequiper : ");
        try {
            int indexEquipement = Integer.parseInt(choixUtilisateur);
            if (indexEquipement == 0) {
                choixValide = true;
            } else if (indexEquipement == 1 && !arme) {
                IO.println("Vous ne pouvez pas desequiper vos mains !");
            } else if (indexEquipement == 1) {
                hero.getEquipementEquipe().desequiperArme();
                choixValide = true;
            } else if (indexEquipement > 0 && indexEquipement < armuresEquipeesView.size() + (arme ? 1 : 0)) {
                hero.desequiperDepuisInventaire(armuresEquipeesView.get(indexEquipement - 1 - (arme ? 1 : 0)));
                choixValide = true;
            } else {
                IO.println("Cet objet n'existe pas...");
            }
        } catch (NumberFormatException _) {
            IO.println("Ce n'est pas un nombre ! x_x");
        }
        return choixValide;
    }

    private boolean afficherArmuresEquipees(boolean arme) {
        if (hero.getEquipementEquipe().getArmuresEquipees().isEmpty()) {
            IO.println("Aucune armure équipée.");
        } else {
            List<Armure> armuresEquipeesView = InventaireViewBuilder.buildEquipementsArmureView(hero.getEquipementEquipe());
            for (int i = 0; i < armuresEquipeesView.size(); i++) {
                IO.println(String.format(SAVE_FILE_FORMAT, i + 1 + (arme ? 1 : 0), armuresEquipeesView.get(i)));
            }
            return true;
        }
        return false;
    }

    private boolean afficherArmeEquipee() {
        IO.println("====° Arme °====");
        if (hero.getEquipementEquipe().getArmeEquipee() != null) {
            IO.println(String.format("1 - %s", hero.getEquipementEquipe().getArmeEquipee()));
            return true;
        } else {
            IO.println("Aucune arme équipée.");
        }
        return false;
    }

    private void afficherSacADos() {
        IO.println("===== SAC A DOS =====");
        if (hero.getInventaire().getEquipements().isEmpty()) {
            IO.println("Votre sac à dos est vide ...");
            return;
        }
        List<ItemEntry> equipementsView = InventaireViewBuilder.buildEquipementsView(hero.getInventaire());

        for (int i = 0; i < equipementsView.size(); i++) {
            IO.println(String.format(SAVE_FILE_FORMAT, i + 1, equipementsView.get(i).equipement()));
        }
        IO.println("O - Retour");
        boolean choixValide = false;
        while (!choixValide) {
            String choixUtilisateur = IO.readln("Choix de l'equipement : ");
            try {
                int indexEquipement = Integer.parseInt(choixUtilisateur);
                if (indexEquipement == 0) {
                    choixValide = true;
                } else if (indexEquipement > 0 && indexEquipement < equipementsView.size()) {
                    hero.equiperDepuisInventaire(equipementsView.get(indexEquipement - 1).equipement());
                    choixValide = true;
                } else {
                    IO.println("Cet objet n'existe pas...");
                }
            } catch (NumberFormatException _) {
                IO.println("Ce n'est pas un nombre ! x_x");
            }

        }
    }

    private char getChoixSacADos() {
        IO.println("1 - Equiper une piece d'equipement");
        IO.println("2 - Desequiper une piece d'equipement");
        IO.println("3 - " + RETOUR);
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
    }

    public char getChoixMenuInventaire() {
        IO.println("1 - Barda");
        IO.println("2 - Consommables");
        IO.println("3 - " + RETOUR);
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
    }

    public char getChoixMenuPrincipal() {
        IO.println("1 - Boutique");
        IO.println("2 - Inventaire");
        IO.println("3 - A l'aventure !");
        IO.println(String.format("4 - Difficulte [%s]", difficulte.getLabel()));
        IO.println("5 - Quitter");
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
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
                default -> IO.println(INVALID_SELECTION_MESSAGE);
            }
        }
        quickSave();
    }

    private char getChoixMenuBoutique() {
        IO.println("1 - Apothicaire");
        IO.println("2 - Forgeron");
        IO.println("3 - Retour");
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
    }

    public void menuConsommables() {
        IO.println("===== APOTHICAIRE =====");
        boolean choixMenuConsommables = false;
        while (!choixMenuConsommables) {
            String choixUtilisateur = getChoixMenuConsommables();
            if (choixUtilisateur.equalsIgnoreCase(RETOUR))
                return;
            if (Potions.contains(choixUtilisateur)) {
                if (boutique.getConsommables().get(apothicaireStandard.melanger(Potions.getByName(choixUtilisateur))) <= 0) {
                    IO.println(String.format("L'apothicaire n'a plus de %s en stock ...", choixUtilisateur));
                } else {
                    choixMenuConsommables = acheterConsommable(choixUtilisateur, false);
                }
            } else {
                IO.println("L'apothicaire ne possède pas cet objet...");
            }
        }

    }

    private boolean acheterConsommable(String choixUtilisateur, boolean choixMenuConsommables) {
        Potion potionChoisie = apothicaireStandard.melanger(Potions.getByName(choixUtilisateur));
        if (hero.getInventaire().getMonnaie() < potionChoisie.prixAchat()) {
            IO.println(String.format("Vous n'avez pas assez d'argent pour acheter %s !", choixUtilisateur));
        } else {
            if (boutique.retirerConsommables(potionChoisie, 1)) {
                hero.getInventaire().ajouterConsommables(potionChoisie, 1);
                hero.getInventaire().retirerMonnaie(potionChoisie.prixAchat());
                IO.println(String.format("Vous avez acheté %s pour %s.", choixUtilisateur, Service.formatMonnaie(potionChoisie.prixAchat())));
                IO.println(String.format("Il vous reste %s.", Service.formatMonnaie(hero.getInventaire().getMonnaie())));
                choixMenuConsommables = true;
            } else {
                IO.println(String.format("L'apothicaire n'a plus assez de %s en stock ...", choixUtilisateur));
            }
        }
        return choixMenuConsommables;
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
            if (choixUtilisateur.equalsIgnoreCase(RETOUR))
                return;
            if (Armurerie.contains(choixUtilisateur)) {
                if (boutique.getEquipements().get(forgeronStandard.forger(Armurerie.getByName(choixUtilisateur))) <= 0) {
                    IO.println(String.format("Le forgeron n'a plus de %s en stock ...", choixUtilisateur));
                } else {
                    choixMenuEquipements = acheterEquipement(choixUtilisateur, false);
                }
            } else {
                IO.println("Le forgeron ne possède pas cet objet...");
            }
        }
    }

    private boolean acheterEquipement(String choixUtilisateur, boolean choixMenuEquipements) {
        Equipement equipementChoisi = forgeronStandard.forger(Armurerie.getByName(choixUtilisateur));
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
        return choixMenuEquipements;
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
