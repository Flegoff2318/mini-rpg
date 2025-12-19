package combat;

import consommables.Consommable;
import consommables.Potion;
import equipements.Equipement;
import personnages.Hero;
import personnages.Monstre;
import personnages.Personnage;
import sorts.*;

import java.util.Comparator;
import java.util.List;

import static services.Service.formatMonnaie;

public class Combat {
    private final Hero hero;
    private final Monstre monstre;
    private boolean tourJoueur;
    private final ContexteSort contexteSort;

    public Combat(Hero attaquant, Monstre defenseur) {
        this.hero = attaquant;
        this.monstre = defenseur;
        tourJoueur = attaquant.getVitesse() >= defenseur.getVitesse();
        contexteSort = new ContexteSort();
    }

    public void debutCombat() {
        while (true) {
            if (tourJoueur) {
                IO.println(String.format("%s %s vous jette un regard noir, qu'allez-vous faire ? (%s Points de vie restants)", monstre.getNom(), monstre.getType(), monstre.getPointsVie()));
                while (true) {
                    char choixUtilisateur = getChoixUtilisateurMenuPrincipal();
                    if (choixUtilisateur == '3') {
                        if (fuir()) {
                            IO.println("Vous prenez la fuite.");
                            return;
                        } else {
                            IO.println("Vous ne pouvez pas fuir le combat.");
                        }
                    } else if (choixUtilisateur == '1') {
                        while (true) {
                            char choixAttaque = getChoixAttaqueMenuAttaque();
                            if (choixAttaque == '3') {
                                break;
                            } else if (choixAttaque == '1') {
                                attaquePhysique();
                                if (finCombat()) {
                                    IO.println(String.format("Vous avez vaincu %s %s !", monstre.getNom(), monstre.getType()));
                                    pillage();
                                    return;
                                }
                                tourJoueur = !tourJoueur;
                                break;
                            } else if (choixAttaque == '2') {
                                boolean isSortChoisi = choixDuSort();
                                if (isSortChoisi) {
                                    if (finCombat()) {
                                        return;
                                    }
                                    tourJoueur = !tourJoueur;
                                }
                                break;
                            }
                        }
                    } else if (choixUtilisateur == '2') {
                        // Choix Consommable
                        IO.println("Repasses plus tard °-°");
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
            }
        }
    }

    public char getChoixAttaqueMenuAttaque() {
        IO.println("1 - Attaquer avec son arme");
        IO.println("2 - Attaquer avec un sort");
        IO.println("3 - Retour");
        return IO.readln().charAt(0);
    }

    public char getChoixUtilisateurMenuPrincipal() {
        IO.println("1 - Attaquer");
        IO.println("2 - Consommables");
        IO.println("3 - Fuir");
        return IO.readln().charAt(0);
    }

    public boolean fuir() {
        return hero.getVitesse() >= monstre.getVitesse();
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
        int pointsVieDefenseur;
        if (tourJoueur) {
            degats = hero.getAttaque() - monstre.getArmure();
            pointsVieDefenseur = monstre.getPointsVie();
            if (degats > 0) {
                monstre.setPointsVie(pointsVieDefenseur - degats);
                IO.println(String.format("Vous avez infligé %s à %s %s !", degats, monstre.getNom(), monstre.getType()));
            } else {
                IO.println("Attaque inéfficace.");
            }
        } else {
            degats = monstre.getAttaque() - hero.getArmure();
            pointsVieDefenseur = hero.getPointsVie();
            if (degats > 0) {
                hero.setPointsVie(pointsVieDefenseur - degats);
                IO.println("Le monstre vous a infligé " + degats + " dégats.");
            } else {
                IO.println("Le monstre ne vous a pas fait de dégats.");
            }
        }
    }


    public boolean choixDuSort() {
        if (tourJoueur) {
            IO.println("Séléctionnez un sort : ");
            afficherSortsDisponibles(hero);
            boolean isSortChoisi = false;
            while (!isSortChoisi) {
                String sortChoisi = IO.readln().toLowerCase();
                if (!sortChoisi.isBlank()) {
                    if (sortChoisi.equalsIgnoreCase("retour")) {
                        isSortChoisi = true;
                    } else {
                        Sort sort = LivreDeSorts.SORTS.get(sortChoisi);
                        if (sort == null) {
                            IO.println("Ce sort n'éxiste pas. Choisis-en un autre !");
                        } else {
                            if (sort.getEffetSort() instanceof EffetSoins) {
                                contexteSort.lancerSort(hero, hero, sort);
                            }else{
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
        return false;
    }

    public void afficherSortsDisponibles(Personnage personnage) {
        LivreDeSorts.SORTS.values().stream()
                .sorted(Sort::compareTo)
                .filter(sort -> sort.getNiveauMinimum() <= personnage.getNiveau())
                .forEach((sort) ->
                        IO.println(String.format("%s - %s points de mana, %s de puissance",
                                sort.getNom(),
                                sort.getCoutMana(),
                                sort.getPuissance()
                        ))

                );
        IO.println("Retour");
    }

    public void pillage() {
        pillerEquipement();
        pillerConsommables();
        pillerMonnaie();
    }

    private void pillerMonnaie() {
        int monnaiePille = monstre.getInventaire().getMonnaie();
        hero.getInventaire().ajouterMonnaie(monnaiePille);
        IO.println("Vous avez récupéré " + formatMonnaie(monnaiePille));
        IO.println(String.format("Nouvelle fortune : %s", formatMonnaie(hero.getInventaire().getMonnaie())));
    }

    private void pillerConsommables() {
        List<Consommable> consommablesPille = monstre.getInventaire().getConsommables();
        hero.getInventaire().getConsommables().addAll(consommablesPille);
        IO.println("Vous avez récupéré " + consommablesPille.size() + " nouveaux consommables.");
    }

    private void pillerEquipement() {
        List<Equipement> equipementsPille = monstre.getInventaire().getEquipements();
        hero.getInventaire().getEquipements().addAll(equipementsPille);
        IO.println("Vous avez récupéré " + equipementsPille.size() + " nouveaux objets.");
    }

    public void utiliserPotion(Personnage utilisateur, Potion potion) {
        if (utilisateur.getInventaire().getConsommables().contains(potion)) {
            utilisateur.setPointsVie(utilisateur.getPointsVie() + potion.getRegenerationVie());
            utilisateur.setMana(utilisateur.getMana() + potion.getRegenerationMana());
            utilisateur.getInventaire().getConsommables().remove(potion);
        }
    }

    public Personnage getHero() {
        return hero;
    }

    public Personnage getMonstre() {
        return monstre;
    }

}
