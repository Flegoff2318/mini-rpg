package sorts;

public class GrimoireStandard implements Grimoire {
    @Override
    public Sort feuilleter(Sorts sorts) {
        return GrimoireCatalogue.getSorts().get(sorts);
    }
}
