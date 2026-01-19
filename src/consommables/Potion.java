package consommables;

import effets.EffetConsommable;

public record Potion(Potions type, String nom, int puissance, int prixVente, int prixAchat,
                     EffetConsommable effetConsommable) implements Comparable<Potion>, Consommable {

    @Override
    public int compareTo(Potion o) {
        return nom.compareTo(o.nom);
    }
}
