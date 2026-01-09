package equipements;


import core.Statistiques;

public record Arme(String nom, Statistiques statistiques, int niveauRequis, int prixAchat, int prixVente) implements Equipement {

}
