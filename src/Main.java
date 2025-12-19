import combat.Combat;
import donjon.Donjon;
import personnages.Hero;
import personnages.Monstre;

void main() {
    Hero hero = new Hero("MonHero");
    hero.setAttaque(200);
    hero.setNiveau(100);
    Donjon donjon = new Donjon(10);
    IO.println("Vous entrez dans un donjon !");
    for (Monstre monstre : donjon.getMonstres()) {
        Combat combat = new Combat(hero, monstre);
        combat.debutCombat();
        if (hero.getPointsVie() <= 0) {
            IO.println("Vous êtes mort.");
            return;
        } else if (donjon.getMonstres().getLast() != monstre) {
            IO.println("Vous entrez dans la salle suivante ...");
        } else {
            IO.println("Vous êtes sorti du donjon sain et sauf.");
        }
    }
}
