import java.util.ArrayList;
import java.util.List;

public class Koszyk {
    private Klient klient;
    private List<PozycjaKoszyka> pozycje;

    // Kompozycja - Koszyk składa się z PozycjaKoszyka
    public static class PozycjaKoszyka {
        private Produkt produkt;
        private int ilosc;

        public PozycjaKoszyka(Produkt produkt, int ilosc) {
            this.produkt = produkt;
            this.ilosc = ilosc;
        }

        public Produkt getProdukt() {
            return produkt;
        }

        public int getIlosc() {
            return ilosc;
        }

        public double getSumaCzesciowa() {
            return produkt.getCena() * ilosc;
        }

        @Override
        public String toString() {
            return String.format("%s, Ilość: %d, Suma: %.2f zł",
                    produkt.getNazwa(), ilosc, getSumaCzesciowa());
        }
    }

    public Koszyk(Klient klient) {
        this.klient = klient;
        this.pozycje = new ArrayList<>();
    }

    public void dodajPozycje(Produkt produkt, int ilosc) {
        if (produkt == null) {
            throw new IllegalArgumentException("Produkt nie może być null");
        }
        if (ilosc <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa od 0");
        }
        if (produkt.getIlosc() < ilosc) {
            throw new IllegalArgumentException("Niewystarczająca ilość w magazynie");
        }

        pozycje.add(new PozycjaKoszyka(produkt, ilosc));
    }

    public void usunPozycje(int index) {
        if (index < 0 || index >= pozycje.size()) {
            throw new IndexOutOfBoundsException("Nieprawidłowy indeks pozycji");
        }
        pozycje.remove(index);
    }

    public double obliczSuma() {
        double suma = 0;
        for (PozycjaKoszyka pozycja : pozycje) {
            suma += pozycja.getSumaCzesciowa();
        }

        // Zastosowanie rabatu dla stałych klientów
        if (klient instanceof KlientStaly) {
            double rabat = ((KlientStaly) klient).getRabat();
            suma *= (1 - rabat);
        }

        return suma;
    }

    public void wyswietlKoszyk() {
        if (pozycje.isEmpty()) {
            System.out.println("Koszyk jest pusty!");
            return;
        }

        System.out.println("\nZAWARTOŚĆ KOSZYKA:");
        for (int i = 0; i < pozycje.size(); i++) {
            System.out.println((i + 1) + ". " + pozycje.get(i));
        }
        System.out.printf("RAZEM: %.2f zł\n", obliczSuma());
    }

    public boolean czyPusty() {
        return pozycje.isEmpty();
    }

    public List<PozycjaKoszyka> getPozycje() {
        return new ArrayList<>(pozycje); // Zwracamy kopię dla bezpieczeństwa
    }
}