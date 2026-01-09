package personnages;

import core.Statistiques;
import equipements.Equipement;
import equipements.EquipementEquipe;
import inventaire.Inventaire;

public abstract class Personnage {
    protected String nom;
    protected int pointsVie;
    protected int mana;
    protected int niveau;
    protected final Inventaire inventaire;
    protected final EquipementEquipe equipementEquipe;
    protected Statistiques statistiques;


    public Personnage(String nom, int pointsVieMax, int manaMax, int attaquePhysiquen, int puissanceMagique, int armure, int resistanceMagique, int vitesse, int niveau) {
        this.nom = nom;
        this.pointsVie = pointsVieMax;
        this.mana = manaMax;
        this.niveau = niveau;
        this.inventaire = new Inventaire();
        this.equipementEquipe = new EquipementEquipe();
        this.statistiques = new Statistiques(pointsVieMax, manaMax, attaquePhysiquen, puissanceMagique, armure, resistanceMagique, vitesse);
    }

    public Personnage(String nom) {
        this.nom = nom;
        this.pointsVie = 100;
        this.mana = 100;
        this.niveau = 1;
        this.inventaire = new Inventaire();
        this.equipementEquipe = new EquipementEquipe();
        this.statistiques = new Statistiques(100, 100, 10, 10, 2, 2, 100);
    }

    public boolean subirDegats(int degats) {
        if (degats > 0) {
            pointsVie -= degats;
            return true;
        } else {
            return false;
        }
    }

    public int soigner(int soins) {
        if (soins + pointsVie >= statistiques.pointsVieMax()) {
            soins = statistiques.pointsVieMax() - pointsVie;
            pointsVie = statistiques.pointsVieMax();
        } else {
            pointsVie += soins;
        }
        return soins;
    }

    public boolean consommerMana(int pointsManaConsomme) {
        if (mana - pointsManaConsomme >= 0) {
            mana -= pointsManaConsomme;
            return true;
        } else {
            return false;
        }
    }

    public Statistiques getStatsEffectives() {
        return statistiques.add(equipementEquipe.getBonusTotal());
    }

    public int getPointsVieMax() {
        return statistiques.pointsVieMax();
    }

    public int getManaMax() {
        return statistiques.manaMax();
    }

    public void clampRessources() {
        this.pointsVie = Math.min(pointsVie, getPointsVieMax());
        this.mana = Math.min(mana, getManaMax());
    }

    public void equiperDepuisInventaire(Equipement equipement) {
        if (!this.getInventaire().contientEquipement(equipement)) {
            IO.println("Vous n'avez pas cet objet.");
            return;
        }
        if (this.niveau < equipement.niveauRequis()) {
            IO.println("Vous n'avez pas le niveau pour équiper cet objet !");
            return;
        }
        inventaire.retirerEquipement(equipement, 1);
        Equipement equipementRemplace = equipementEquipe.equiper(equipement);
        if (equipementRemplace != null) {
            inventaire.ajouterEquipement(equipementRemplace, 1);
        }
        // Permet de rééquilibrer les stats en fonction du nouvel équipement équipé.
        clampRessources();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointsVie() {
        return pointsVie;
    }

    public int getMana() {
        return mana;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public EquipementEquipe getEquipementEquipe() {
        return equipementEquipe;
    }

    public Statistiques getStatistiques() {
        return statistiques;
    }

    public void setStatistiques(Statistiques statistiques) {
        this.statistiques = statistiques;
    }
}
