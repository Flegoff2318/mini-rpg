package equipements;

public abstract class Equipement {
    protected String nom;
    protected int vitesse;
    protected  int niveauMinimum;
    protected int prixAchat;
    protected int prixVente;

    public Equipement(String nom, int vitesse, int niveauMinimum, int prixAchat, int prixVente) {
        this.nom = nom;
        this.vitesse = vitesse;
        this.niveauMinimum = niveauMinimum;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getNiveauMinimum() {
        return niveauMinimum;
    }

    public void setNiveauMinimum(int niveauMinimum) {
        this.niveauMinimum = niveauMinimum;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }
}
