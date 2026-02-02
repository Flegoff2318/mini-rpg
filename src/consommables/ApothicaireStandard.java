package consommables;

public class ApothicaireStandard implements Apothicaire{
    @Override
    public Potion melanger(Potions potions) {
        return ApothicaireCatalogue.getEtagere().get(potions);
    }
}
