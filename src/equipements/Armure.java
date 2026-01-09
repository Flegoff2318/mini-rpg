package equipements;

import core.Statistiques;

public record Armure(String nom, EmplacementArmure emplacementArmure, Statistiques statistiques, int niveauRequis, int prixAchat, int prixVente) implements Equipement{

}
