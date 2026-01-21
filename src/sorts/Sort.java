package sorts;

import effets.EffetSort;
import personnages.Archetype;

public record Sort(String nom, int coutMana, int puissance, int niveauMinimum,
                   EffetSort effetSort, Archetype archetype) implements Comparable<Sort> {

    @Override
    public String toString() {
        return "Sort de niveau " + niveauMinimum + ". " + nom + " : " + puissance + " de puissance. Coût en mana : " + coutMana;
    }

    @Override
    public int compareTo(Sort o) {
        return this.niveauMinimum - o.niveauMinimum;
    }
}
