package equipements;

public class Armure extends Equipement{
    private int armure;
    private int resistanceMagique;
    private final EmplacementArmure emplacementArmure;

    public Armure(String nom, int vitesse, int niveauMinimum, int prixAchat, int prixVente, int armure, int resistanceMagique, EmplacementArmure emplacementArmure) {
        super(nom, vitesse, niveauMinimum, prixAchat, prixVente);
        this.armure = armure;
        this.resistanceMagique = resistanceMagique;
        this.emplacementArmure = emplacementArmure;
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

    public EmplacementArmure getEmplacementArmure() {
        return emplacementArmure;
    }
}
