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
    protected Archetype archetype;


    public Personnage(String nom, int pointsVieMax, int manaMax, int attaquePhysique, int puissanceMagique, int armure, int resistanceMagique, int vitesse, int niveau, Archetype archetype) {
        this.nom = nom;
        this.pointsVie = pointsVieMax;
        this.mana = manaMax;
        this.niveau = niveau;
        this.archetype = archetype;
        this.inventaire = new Inventaire();
        this.equipementEquipe = new EquipementEquipe();
        this.statistiques = new Statistiques(pointsVieMax, manaMax, attaquePhysique, puissanceMagique, armure, resistanceMagique, vitesse);
    }

    public Personnage(String nom) {
        this.nom = nom;
        this.pointsVie = 100;
        this.mana = 100;
        this.niveau = 1;
        this.inventaire = new Inventaire();
        this.equipementEquipe = new EquipementEquipe();
        this.statistiques = new Statistiques(100, 100, 10, 10, 2, 2, 100);
        this.archetype = Archetype.GUERRIER;
    }

    public Personnage(String nom, Archetype archetype) {
        this.nom = nom;
        this.niveau = 1;
        this.archetype = archetype;
        this.inventaire = new Inventaire();
        this.equipementEquipe = new EquipementEquipe();
        this.statistiques = switch (archetype) {
            case MAGE -> new Statistiques(100, 150, 10, 25, 5, 15, 100);
            case ASSASSIN -> new Statistiques(60, 50, 25, 10, 10, 10, 200);
            case GUERRIER -> new Statistiques(150, 30, 35, 0, 15, 5, 100);
        };
        this.pointsVie = statistiques.pointsVieMax();
        this.mana = statistiques.manaMax();
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

    public int recupererMana(int manaRecupere){
        if (manaRecupere + this.mana>=statistiques.manaMax()){
            manaRecupere = statistiques.manaMax() - this.mana;
            mana = statistiques.manaMax();
        }else{
            mana+= manaRecupere;
        }
        return manaRecupere;
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

    public void desequiperDepuisInventaire(Equipement equipement) {
        if (!this.getInventaire().contientEquipement(equipement)) {
            IO.print("Vous n'avez pas cet objet.");
            return;
        }
        Equipement equipementDesequipe = equipementEquipe.desequiper(equipement);
        if (equipementDesequipe != null) {
            IO.println(String.format("Vous avez ranger votre %s dans votre sac.", equipementDesequipe.nom()));
            inventaire.ajouterEquipement(equipementDesequipe, 1);
        }
        clampRessources();
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
            IO.println(String.format("Vous avez equipe votre %s et rangé %s dans votre sac.", equipement.nom(), equipementRemplace.nom()));
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
