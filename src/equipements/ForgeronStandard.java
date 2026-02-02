package equipements;

public class ForgeronStandard implements Forgeron{
    @Override
    public Equipement forger(Armurerie armurerie) {
        return ForgeronCatalogue.getRatelier().get(armurerie);
    }
}
