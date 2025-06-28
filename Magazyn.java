import java.util.ArrayList;
import java.util.List;

public class Magazyn {
    private List<Produkt> produkty;

    public Magazyn() {
        this.produkty = new ArrayList<>();
    }

    // CRUD operations
    public void dodajProdukt(Produkt produkt) {
        produkty.add(produkt);
    }

    public boolean usunProdukt(int id) {
        Produkt produkt = getProduktById(id);
        if (produkt != null) {
            return produkty.remove(produkt);
        }
        return false;
    }

    public Produkt getProduktById(int id) {
        for (Produkt produkt : produkty) {
            if (produkt.getId() == id) {
                return produkt;
            }
        }
        return null;
    }

    public void wyswietlProdukty() {
        if (produkty.isEmpty()) {
            System.out.println("Brak produktów w magazynie!");
            return;
        }

        System.out.println("\nPRODUKTY W MAGAZYNIE:");
        for (Produkt produkt : produkty) {
            System.out.println(produkt);
        }
    }

    public void aktualizujStan(Koszyk koszyk) {
        for (Koszyk.PozycjaKoszyka pozycja : koszyk.getPozycje()) {
            Produkt produkt = pozycja.getProdukt();
            produkt.setIlosc(produkt.getIlosc() - pozycja.getIlosc());
        }
    }
}