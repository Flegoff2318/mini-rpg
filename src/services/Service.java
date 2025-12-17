package services;

public class Service {
    public String formatMonnaie(int monnaie) {
        int or;
        int argent;
        int cuivre;
        argent = monnaie / 100;
        cuivre = monnaie % 100;
        or = argent / 100;
        argent %= 100;
        return (or + "po, " + argent + "pa, " + cuivre + "pc");
    }
}