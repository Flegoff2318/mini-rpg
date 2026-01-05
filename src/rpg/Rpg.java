package rpg;

import combat.Combat;
import consommables.Consommable;
import donjon.Donjon;
import effets.EffetConsommableSoins;
import personnages.Hero;
import personnages.Monstre;

import java.util.HashMap;
import java.util.Map;

public class Rpg {
    private Hero hero;

    public void play(){
        testData();
        menuPrincipal();
    }

    public void testData(){
        // TODO : Type de hero, caster/physical/merchant/assassin
        hero = new Hero("Jerry");
        hero.setAttaque(100);
        hero.setNiveau(1);

        Consommable potionDeVieMineure = new Consommable("potion de vie mineure",20,100,200,new EffetConsommableSoins());
        Consommable potionDeVieSuperieure = new Consommable("potion de vie superieure",50,250,500,new EffetConsommableSoins());
        Map<Consommable,Integer> mapConsommables = new HashMap<>(
                Map.ofEntries(
                        Map.entry(potionDeVieMineure,3),
                        Map.entry(potionDeVieSuperieure,3)
                )
        );
        hero.getInventaire().ajouterConsommables(mapConsommables);
    }

    public boolean menuPrincipal(){
        boolean choixMenu=false;
        while(!choixMenu){
            // Boutique

            // Achat & Revente

            // Combats
            Donjon donjon = new Donjon(10);
            IO.println("Vous entrez dans un donjon !");
            for (Monstre monstre : donjon.getMonstres()) {
                Combat combat = new Combat(hero, monstre);
                combat.debutCombat();
                if (hero.getPointsVie() <= 0) {
                    IO.println("Vous êtes mort.");
                    break;
                } else if (donjon.getMonstres().getLast() != monstre) {
                    IO.println("Vous entrez dans la salle suivante ...");
                } else {
                    IO.println("Vous êtes sorti du donjon sain et sauf.");
                    choixMenu=true;
                }
            }
            // Selection de la difficulté
            // Quitter

        }
        return false;
    }

    public void menuBoutique(){

    }
}
