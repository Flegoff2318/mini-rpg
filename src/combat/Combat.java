package combat;

import consommables.Consommable;
import consommables.ContexteConsommable;
import effets.EffetConsommableMana;
import effets.EffetConsommableSoins;
import effets.EffetSortSoins;
import equipements.Equipement;
import personnages.Hero;
import personnages.Monstre;
import personnages.Personnage;
import sorts.ContexteSort;
import sorts.Grimoire;
import sorts.Sort;

import java.util.List;
import java.util.Map;

import static services.Service.formatMonnaie;

public class Combat {
    private final Hero hero;
    private final Monstre monstre;
    private boolean tourJoueur;
    private final ContexteSort contexteSort;
    private final ContexteConsommable contexteConsommable;

    public Combat(Hero attaquant, Monstre defenseur) {
        this.hero = attaquant;
        this.monstre = defenseur;
        this.contexteConsommable = new ContexteConsommable();
        tourJoueur = attaquant.getStatsEffectives().vitesse() >= defenseur.getStatsEffectives().vitesse();
        contexteSort = new ContexteSort();
    }

    public boolean debutCombat() {
        while (true) {
            if (tourJoueur) {
                IO.println(String.format("%s %s vous jette un regard noir, qu'allez-vous faire ? (%s Points de vie restants)", monstre.getNom(), monstre.getType(), monstre.getPointsVie()));
                IO.println(String.format("Il vous reste %s points de vie.", hero.getPointsVie()));
                while (true) {
                    char choixUtilisateur = getChoixUtilisateurMenuPrincipal();
                    if (choixUtilisateur == '4') {
                        return true;
                    }
                    if (choixUtilisateur == '3') {
                        if (fuir()) {
                            IO.println("Vous prenez la fuite.");
                            return false;
                        } else {
                            IO.println("Vous ne pouvez pas fuir le combat.");
                        }
                    } else if (choixUtilisateur == '1') {
                        boolean choixMenuAttaque = false;
                        while (!choixMenuAttaque) {
                            char choixAttaque = getChoixAttaqueMenuAttaque();
                            switch (choixAttaque) {
                                case '1' -> {
                                    attaquePhysique();
                                    if (finCombat()) {
                                        return false;
                                    }
                                    tourJoueur = !tourJoueur;
                                    choixMenuAttaque = true;
                                }
                                case '2' -> {
                                    boolean isSortChoisi = choixDuSort();
                                    if (isSortChoisi) {
                                        if (finCombat()) {
                                            return false;
                                        }
                                        tourJoueur = !tourJoueur;
                                    }
                                    choixMenuAttaque = true;
                                }
                                case '3' -> choixMenuAttaque = true;
                            }
                        }
                    } else if (choixUtilisateur == '2') {
                        // Choix Consommable
                        choixConsommable();
                        break;
                    } else {
                        IO.println("erreur x_x");
                    }
                    if (!tourJoueur) {
                        break;
                    }
                }
            } else {
                // Tour IA
                attaquePhysique();
                tourJoueur = !tourJoueur;
            }
        }
    }

    public void choixConsommable() {
        if (hero.getInventaire().getConsommables().isEmpty()) {
            IO.println("Vous n'avez aucun consommables...");
        } else {
            boolean choixValide = false;
            while (!choixValide) {
                String choixUtilisateur = getChoixConsommableMenuConsommable();
                if (choixUtilisateur.equalsIgnoreCase("retour"))
                    break;
                Consommable consommable = hero.getInventaire().getConsommables().keySet().stream()
                        .filter(c -> c.nom().equalsIgnoreCase(choixUtilisateur))
                        .findFirst()
                        .orElse(null);
                if (consommable == null) {
                    IO.println("Ce consommable n'existe pas.");
                } else {
                    switch (consommable.effetConsommable()) {
                        case EffetConsommableSoins _, EffetConsommableMana _ ->
                                contexteConsommable.utiliserConsommable(hero, hero, consommable);
                        default -> contexteConsommable.utiliserConsommable(hero, monstre, consommable);
                    }
                    choixValide = true;
                }
            }
        }
    }

    public String getChoixConsommableMenuConsommable() {
        hero.getInventaire().getConsommables().forEach((consommable, nombre) -> {
            IO.println(String.format("%s - %s (Quantité : %s)", consommable.nom(), consommable.puissance(), nombre));
        });
        return IO.readln();
    }


    public char getChoixAttaqueMenuAttaque() {
        IO.println("1 - Attaquer en melee");
        IO.println("2 - Grimoire");
        IO.println("3 - Retour");
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
    }

    public char getChoixUtilisateurMenuPrincipal() {
        IO.println("1 - Actions de combat");
        IO.println("2 - Consommables");
        IO.println("3 - Fuir");
        IO.println("4 - Quitter le Donjon");
        String str = IO.readln();
        return str.isBlank() ? 'x' : str.charAt(0);
    }

    public boolean fuir() {
        return hero.getStatsEffectives().vitesse() >= monstre.getStatsEffectives().vitesse();
    }

    public boolean finCombat() {
        boolean finCombat = hero.getPointsVie() <= 0 || monstre.getPointsVie() <= 0;
        if (finCombat) {
            IO.println(String.format("Vous avez vaincu %s %s !", monstre.getNom(), monstre.getType()));
            pillage();
        }
        return finCombat;
    }

    public void attaquePhysique() {
        int degats;
        if (tourJoueur) {
            degats = hero.getStatsEffectives().attaquePhysique() - monstre.getStatsEffectives().armure();
            if (degats > 0) {
                monstre.subirDegats(degats);
                IO.println(String.format("Vous avez infligé %s degats à %s %s !", degats, monstre.getNom(), monstre.getType()));
            } else {
                IO.println("Attaque inéfficace.");
            }
        } else {
            degats = monstre.getStatsEffectives().attaquePhysique() - hero.getStatsEffectives().armure();
            if (degats > 0) {
                hero.subirDegats(degats);
                IO.println(String.format("%s %s vous a infligé %s degats !", monstre.getNom(), monstre.getType(), degats));
            } else {
                IO.println("Le monstre ne vous a pas fait de dégats.");
            }
        }
    }


    public boolean choixDuSort() {
        boolean isSortChoisi = false;
        if (tourJoueur) {
            IO.println("Séléctionnez un sort : ");
            afficherSortsDisponibles(hero);
            while (!isSortChoisi) {
                String sortChoisi = IO.readln().toLowerCase();
                if (!sortChoisi.isBlank()) {
                    if (sortChoisi.equalsIgnoreCase("retour")) {
                        break;
                    } else {
                        Sort sort = Grimoire.SORTS.get(sortChoisi);
                        if (sort == null) {
                            IO.println("Ce sort n'éxiste pas. Choisis-en un autre !");
                        } else if (hero.getNiveau() < sort.niveauMinimum()) {
                            IO.println("Vous n'avez pas encore apprit ce sort !");
                        } else {
                            if (sort.effetSort() instanceof EffetSortSoins) {
                                contexteSort.lancerSort(hero, hero, sort);
                            } else {
                                contexteSort.lancerSort(hero, monstre, sort);
                            }
                            isSortChoisi = true;
                        }
                    }
                }
            }
        } else {
            // Tour IA
            return false;
        }
        return isSortChoisi;
    }

    public void afficherSortsDisponibles(Personnage personnage) {
        Grimoire.SORTS.values().stream()
                .sorted(Sort::compareTo)
                .filter(sort -> sort.niveauMinimum() <= personnage.getNiveau())
                .filter(sort -> sort.archetype() == null || sort.archetype() == personnage.getArchetype())
                .forEach((sort) ->
                        IO.println(String.format("%s - %s points de mana, %s de puissance",
                                sort.nom(),
                                sort.coutMana(),
                                sort.puissance()
                        ))

                );
        IO.println("Retour");
    }

    public void pillage() {
        pillerExperience();
        pillerEquipement();
        pillerConsommables();
        pillerMonnaie();
    }

    private void pillerExperience() {
        int experienceGagnee = 20 * monstre.getNiveau();
        hero.gagnerExperience(experienceGagnee);
    }

    private void pillerMonnaie() {
        int monnaiePille = monstre.getInventaire().getMonnaie();
        if (monnaiePille > 0) {
            hero.getInventaire().ajouterMonnaie(monnaiePille);
            IO.println("Vous avez récupéré " + formatMonnaie(monnaiePille));
            IO.println(String.format("Nouvelle fortune : %s", formatMonnaie(hero.getInventaire().getMonnaie())));
        }
    }

    private void pillerConsommables() {
        Map<Consommable, Integer> consommablesPille = monstre.getInventaire().getConsommables();
        if (!consommablesPille.isEmpty()) {
            hero.getInventaire().ajouterConsommables(consommablesPille);
            int nombreConsommablesAjoutes = consommablesPille.values().stream().mapToInt(Integer::intValue).sum();
            IO.println("Vous avez récupéré " + nombreConsommablesAjoutes + " nouveaux consommables.");
        }
    }

    private void pillerEquipement() {
        Map<Equipement, Integer> equipementsPille = monstre.getInventaire().getEquipements();
        if (!equipementsPille.isEmpty()) {
            equipementsPille.forEach((equipement, integer) -> hero.getInventaire().ajouterEquipement(equipement, integer));
            int nombreEquipementsAjoutes = equipementsPille.values().stream().mapToInt(Integer::intValue).sum();
            IO.println("Vous avez récupéré " + nombreEquipementsAjoutes + " nouveaux objets.");
        }
    }

    public Personnage getHero() {
        return hero;
    }

    public Personnage getMonstre() {
        return monstre;
    }

}
