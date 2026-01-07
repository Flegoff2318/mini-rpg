package personnages;

import equipements.EquipementEquipe;
import inventaire.Inventaire;

public abstract class Personnage {
    protected String nom;
    protected int pointsVieMax;
    protected int pointsVie;
    protected int manaMax;
    protected int mana;
    protected int attaque;
    protected int armure;
    protected int resistanceMagique;
    protected int niveau;
    protected int vitesse;
    protected Inventaire inventaire;
    protected EquipementEquipe equipementEquipe;



    public Personnage() {

    }

    public Personnage(String nom) {
        this.nom = nom;
        pointsVieMax = 100;
        pointsVie = 100;
        manaMax = 100;
        mana = 100;
        attaque = 10;
        armure = 2;
        resistanceMagique = 2;
        niveau = 1;
        vitesse = 100;
        inventaire = new Inventaire();
    }

    public boolean subirDegats(int degats) {
        if (degats > 0) {
            pointsVie -= degats;
            return true;
        } else {
            return false;
        }
    }

    public int soigner(int soins){
        if(soins+pointsVie>=pointsVieMax){
            soins = pointsVieMax-pointsVie;
            pointsVie=pointsVieMax;
        }else{
            pointsVie+=soins;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointsVieMax() {
        return pointsVieMax;
    }

    public void setPointsVieMax(int pointsVieMax) {
        this.pointsVieMax = pointsVieMax;
    }

    public int getPointsVie() {
        return pointsVie;
    }

    public void setPointsVie(int pointsVie) {
        this.pointsVie = Math.min(pointsVie, pointsVieMax);
    }

    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.min(mana, manaMax);
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getArmure() {
        return armure;
    }

    public void setArmure(int armure) {
        this.armure = armure;
    }

    public int getResistanceMagique() {
        return resistanceMagique;
    }

    public void setResistanceMagique(int resistanceMagique) {
        this.resistanceMagique = resistanceMagique;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public EquipementEquipe getEquipementEquipe() {
        return equipementEquipe;
    }

    public void setEquipementEquipe(EquipementEquipe equipementEquipe) {
        this.equipementEquipe = equipementEquipe;
    }
}
