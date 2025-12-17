package consommables;

public class Potion {
    private int regenerationVie;
    private int regenerationMana;

    public Potion() {
    }

    public Potion(int regenerationVie, int regenerationMana) {
        this.regenerationVie = regenerationVie;
        this.regenerationMana = regenerationMana;
    }

    public int getRegenerationVie() {
        return regenerationVie;
    }

    public void setRegenerationVie(int regenerationVie) {
        this.regenerationVie = regenerationVie;
    }

    public int getRegenerationMana() {
        return regenerationMana;
    }

    public void setRegenerationMana(int regenerationMana) {
        this.regenerationMana = regenerationMana;
    }
}
