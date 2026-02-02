package services;

import java.util.Random;

public class Service {

    private static final Random rand;

    static {
        rand = new Random();
    }

    private Service(){
    }

    public static String formatMonnaie(int monnaie) {
        int or;
        int argent;
        int cuivre;
        argent = monnaie / 100;
        cuivre = monnaie % 100;
        or = argent / 100;
        argent %= 100;
        return String.format("%spo, %spa, %spc", or, argent, cuivre);
    }

    public static Random getRand() {
        return rand;
    }
}