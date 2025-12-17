package sorts;

public class Sort {
    private String nom;
    private int coutMana;
    private int puissance;
    private int niveauMinimum;

    public Sort() {
    }

    public Sort(String nom, int couMana, int puissance, int niveauMinimum) {
        this.nom = nom;
        this.coutMana = couMana;
        this.puissance = puissance;
        this.niveauMinimum = niveauMinimum;
    }

    @Override
    public String toString() {
        return "Sort de niveau " + niveauMinimum + ". " + nom + " : " + puissance + " de puissance. Coût en mana : " + coutMana;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCoutMana() {
        return coutMana;
    }

    public void setCoutMana(int coutMana) {
        this.coutMana = coutMana;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getNiveauMinimum() {
        return niveauMinimum;
    }

    public void setNiveauMinimum(int niveauMinimum) {
        this.niveauMinimum = niveauMinimum;
    }
}
