import consommables.Consommable;
import consommables.Potion;
import equipements.Equipement;
import personnages.Hero;
import personnages.Monstre;
import services.Service;
import personnages.Personnage;
import sorts.Sort;

import java.util.List;

public class Combat {
    private Hero hero;
    private Monstre monstre;
    private boolean tourJoueur;

    public Combat(Hero attaquant, Monstre defenseur) {
        this.hero = attaquant;
        this.monstre = defenseur;
        tourJoueur = attaquant.getVitesse() >= defenseur.getVitesse();
    }

    public void debutCombat() {
        while (true) {
            if (tourJoueur) {
                IO.println(String.format("%s %s vous jette un regard noir, qu'allez-vous faire ? (%s Points de vie restants)", monstre.getNom(), monstre.getType(), monstre.getPointsVie()));
                while (true) {
                    IO.println("1 - Attaquer");
                    IO.println("2 - Consommables");
                    IO.println("3 - Fuir");
                    char choixUtilisateur = IO.readln().charAt(0);
                    if (choixUtilisateur == '3') {
                        if (fuir()) {
                            IO.println("Vous prenez la fuite.");
                            return;
                        } else {
                            IO.println("Vous ne pouvez pas fuir le combat.");
                        }
                    } else if (choixUtilisateur == '1') {
                        while (true) {
                            IO.println("1 - Attaquer avec son arme");
                            IO.println("2 - Attaquer avec un sort");
                            IO.println("3 - Retour");
                            char choixAttaque = IO.readln().charAt(0);
                            if (choixAttaque == '3') {
                                break;
                            } else if (choixAttaque == '1') {
                                attaquePhysique();
                                if (finCombat()) {
                                    IO.println(String.format("Vous avez vaincu %s %s !", monstre.getNom(), monstre.getType()));
                                    pillage();
                                    return;
                                }
                                tourJoueur = false;
                                break;
                            } else if (choixAttaque == '2') {
                                while (true) {
                                    List<Sort> sortsAttaquant = hero.getSorts();
                                    if (sortsAttaquant.isEmpty()) {
                                        IO.println("Vous ne connaissez aucuns sorts ...");
                                        break;
                                    } else {
                                        IO.println("Choisissez un sort :");
                                        int nombreDeSorts = sortsAttaquant.size();
                                        for (int i = 0; i < nombreDeSorts; i++) {
                                            IO.println((i + 1) + " " + sortsAttaquant.get(i));
                                        }
                                        IO.println(nombreDeSorts + 1 + " - Retour");
                                        String inputSort = IO.readln();
                                        int choixSort = Integer.parseInt(inputSort);
                                        if (choixSort == nombreDeSorts + 1) {
                                            break;
                                        } else if (choixSort > 0 && choixSort <= nombreDeSorts) {
                                            Sort sortALancer = hero.getSorts().get(choixSort - 1);
                                            if (hero.getMana() < sortALancer.getCoutMana()) {
                                                IO.println("Pas assez de mana.");
                                            } else {
                                                lancerUnSort(sortALancer);
                                                if (finCombat()) {
                                                    IO.println(String.format("Vous avez vaincu %s %s !", monstre.getNom(), monstre.getType()));
                                                    pillage();
                                                    return;
                                                }
                                                tourJoueur = false;
                                                break;
                                            }
                                        } else {
                                            IO.println("Ce sort n'existe pas.");
                                        }
                                    }
                                }
                            }
                        }
                    } else if (choixUtilisateur == '2') {
                        // Choix Consommable
                        IO.println("Non implémenté.");
                        break;
                    } else {
                        IO.println("Ce choix n'est pas dans la liste.");
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

    public boolean fuir() {
        return hero.getVitesse() >= monstre.getVitesse();
    }

    public boolean finCombat() {
        return hero.getPointsVie() <= 0 || monstre.getPointsVie() <= 0;
    }

    public void attaquePhysique() {
        int degats;
        int pointsVieDefenseur;
        if (tourJoueur) {
            degats = hero.getAttaque() - monstre.getArmure();
            pointsVieDefenseur = monstre.getPointsVie();
            if (degats > 0) {
                monstre.setPointsVie(pointsVieDefenseur - degats);
                IO.println(String.format("Vous avez infligé %s à %s %s !",degats, monstre.getNom(), monstre.getType()));
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
        tourJoueur = !tourJoueur;
    }

    public void lancerUnSort(Sort sort) {
        int degats;
        int pointsVieDefenseur;
        if (tourJoueur) {
            degats = sort.getPuissance() - monstre.getResistanceMagique();
            pointsVieDefenseur = monstre.getPointsVie();
            if (degats > 0) {
                monstre.setPointsVie(pointsVieDefenseur - degats);
                IO.println(String.format("Votre %s a infligé %s à %s %s !",sort.getNom(),degats, monstre.getNom(), monstre.getType()));
            } else {
                IO.println(String.format("Votre %s n'a eu aucun effet sur %s %s.",sort.getNom(), monstre.getNom(), monstre.getType()));
            }
        } else {
            degats = sort.getPuissance() - hero.getResistanceMagique();
            pointsVieDefenseur = hero.getPointsVie();
            if (degats > 0) {
                hero.setPointsVie(pointsVieDefenseur - degats);
                IO.println(String.format("Le sort %s de %s %s vous a infligé %s dégats!",sort.getNom(), monstre.getNom(), monstre.getType(),degats));
            } else {
                IO.println(String.format("Le sort %s de %s %s n'a eu aucun effet.",sort.getNom(), monstre.getNom(), monstre.getType()));
            }
        }
        tourJoueur = !tourJoueur;
    }

    public void pillage() {
        Service service = new Service();
        List<Equipement> equipementsPille = monstre.getInventaire().getEquipements();
        List<Consommable> consommablesPille = monstre.getInventaire().getConsommables();
        int monnaiePille = monstre.getInventaire().getMonnaie();
        int monnaieAttaquant = hero.getInventaire().getMonnaie();
        hero.getInventaire().getEquipements().addAll(equipementsPille);
        IO.println("Vous avez récupéré " + equipementsPille.size() + " nouveaux objets.");
        hero.getInventaire().getConsommables().addAll(consommablesPille);
        IO.println("Vous avez récupéré " + consommablesPille.size() + " nouveaux consommables.");
        hero.getInventaire().setMonnaie(monnaieAttaquant + monnaiePille);
        IO.println("Vous avez récupéré " + service.formatMonnaie(monnaiePille));
        IO.println(String.format("Nouvelle fortune : %s", service.formatMonnaie(hero.getInventaire().getMonnaie())));
    }

    public void utiliserPotion(Personnage utilisateur, Potion potion) {
        utilisateur.setPointsVie(utilisateur.getPointsVie() + potion.getRegenerationVie());
        utilisateur.setMana(utilisateur.getMana() + potion.getRegenerationMana());
        utilisateur.getInventaire().getConsommables().remove(potion);
    }

    public Personnage getHero() {
        return hero;
    }

    public Personnage getMonstre() {
        return monstre;
    }

}
