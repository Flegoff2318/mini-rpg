package equipements;

import core.Statistiques;

public sealed interface Equipement permits Arme,Armure{
    String nom();
    Statistiques statistiques();
    int niveauRequis();
    int prixVente();
    int prixAchat();
}
