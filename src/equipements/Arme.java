package equipements;

public class Arme extends Equipement{
    private int puissancePhysique;
    private int puissanceMagique;

    public Arme(String nom, int vitesse, int niveauMinimum, int prixAchat, int prixVente, int puissancePhysique, int puissanceMagique) {
        super(nom, vitesse, niveauMinimum, prixAchat, prixVente);
        this.puissancePhysique = puissancePhysique;
        this.puissanceMagique = puissanceMagique;
    }
}
