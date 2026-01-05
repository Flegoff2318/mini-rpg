package services;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Service {
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

    /**
     * This method uses the equals() method to compare naturally each element of the list to the object given in parameters.
     *
     * @param list List of T objects.
     * @param o    Any object of type T.
     * @param <T>  Any type of object.
     * @return Returns 0 if list is empty or element "o" of type T is not found in the list, else returns the number of duplicates.
     */
    public static <T> int compterNombreObjets(List<T> list, T o) {
        AtomicInteger compteur = new AtomicInteger(0);
        if (!list.isEmpty()) {
            list.forEach(element -> {
                if (element.equals(o)) {
                    compteur.incrementAndGet();
                }
            });
        }
        return compteur.get();
    }
}