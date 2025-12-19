package sorts;

public class Sort implements Comparable<Sort>{
    private final String nom;
    private final int coutMana;
    private final int puissance;
    private final int niveauMinimum;
    private final EffetSort effetSort;


    public Sort(String nom, int coutMana, int puissance, int niveauMinimum, EffetSort effetSort) {
        this.nom = nom;
        this.coutMana = coutMana;
        this.puissance = puissance;
        this.niveauMinimum = niveauMinimum;
        this.effetSort = effetSort;
    }

    @Override
    public String toString() {
        return "Sort de niveau " + niveauMinimum + ". " + nom + " : " + puissance + " de puissance. Coût en mana : " + coutMana;
    }

    public String getNom() {
        return nom;
    }

    public int getCoutMana() {
        return coutMana;
    }

    public int getPuissance() {
        return puissance;
    }

    public int getNiveauMinimum() {
        return niveauMinimum;
    }

    public EffetSort getEffetSort() {
        return effetSort;
    }

    @Override
    public int compareTo(Sort o) {
        return this.niveauMinimum-o.niveauMinimum;
    }
}
