package consommables;

import effets.EffetConsommable;

public record Consommable(String nom, int puissance, int prixVente, int prixAchat,
                          EffetConsommable effetConsommable) implements Comparable<Consommable> {

    @Override
    public int compareTo(Consommable o) {
        return nom.compareTo(o.nom);
    }
}
