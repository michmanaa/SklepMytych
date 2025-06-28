public class Produkt {
    private int id;
    private String nazwa;
    private double cena;
    private int ilosc;
    private static int nextId = 1;

    // Przeciążony konstruktor
    public Produkt(String nazwa, double cena) {
        this(nazwa, cena, 0);
    }

    public Produkt(String nazwa, double cena, int ilosc) {
        this.id = nextId++;
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
    }

    // Gettery i settery z hermetyzacją
    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa != null && !nazwa.isEmpty()) {
            this.nazwa = nazwa;
        }
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        if (cena >= 0) {
            this.cena = cena;
        }
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        if (ilosc >= 0) {
            this.ilosc = ilosc;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nazwa: %s, Cena: %.2f zł, Ilość: %d", id, nazwa, cena, ilosc);
    }
}
