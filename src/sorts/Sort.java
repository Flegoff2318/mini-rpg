package sorts;

import effets.EffetSort;

public record Sort(String nom, int coutMana, int puissance, int niveauMinimum,
                   EffetSort effetSort) implements Comparable<Sort> {

    @Override
    public String toString() {
        return "Sort de niveau " + niveauMinimum + ". " + nom + " : " + puissance + " de puissance. Coût en mana : " + coutMana;
    }

    @Override
    public int compareTo(Sort o) {
        return this.niveauMinimum - o.niveauMinimum;
    }
}
