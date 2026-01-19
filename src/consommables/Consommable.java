package consommables;

import effets.EffetConsommable;

public sealed interface Consommable permits Potion {
    Potions type();
    String nom();
    int puissance();
    int prixVente();
    int prixAchat();
    EffetConsommable effetConsommable();
}
