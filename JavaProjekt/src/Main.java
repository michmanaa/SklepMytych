import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static Magazyn magazyn = new Magazyn();
    private static List<Klient> klienci = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Dodanie przykładowych produktów
        magazyn.dodajProdukt(new Produkt("Mleko", 3.50, 100));
        magazyn.dodajProdukt(new Produkt("Chleb", 4.20, 50));
        magazyn.dodajProdukt(new Produkt("Jajka", 0.50, 200));

        // Dodanie przykładowych klientów
        klienci.add(new KlientStaly("Jan Kowalski", "mytych14@gmail13.com", 1000.0, 0.1));

        boolean wyjscie = false;
        while (!wyjscie) {
            System.out.println("\n=== SYSTEM ZARZĄDZANIA SKLEPEM ===");
            System.out.println("1. Zarządzaj produktami");
            System.out.println("2. Zarządzaj klientami");
            System.out.println("3. Sprzedaż detaliczna");
            System.out.println("4. Sprzedaż hurtowa");
            System.out.println("5. Wyjście");
            System.out.print("Wybierz opcję: ");

            int wybor = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (wybor) {
                case 1:
                    zarzadzajProduktami();
                    break;
                case 2:
                    zarzadzajKlientami();
                    break;
                case 3:
                    sprzedazDetaliczna();
                    break;
                case 4:
                    sprzedazHurtowa();
                    break;
                case 5:
                    wyjscie = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór!");
            }
        }
    }

    private static void zarzadzajProduktami() {
        boolean powrot = false;
        while (!powrot) {
            System.out.println("\n=== ZARZĄDZANIE PRODUKTAMI ===");
            System.out.println("1. Wyświetl wszystkie produkty");
            System.out.println("2. Dodaj produkt");
            System.out.println("3. Edytuj produkt");
            System.out.println("4. Usuń produkt");
            System.out.println("5. Powrót");
            System.out.print("Wybierz opcję: ");

            int wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    magazyn.wyswietlProdukty();
                    break;
                case 2:
                    dodajProdukt();
                    break;
                case 3:
                    edytujProdukt();
                    break;
                case 4:
                    usunProdukt();
                    break;
                case 5:
                    powrot = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór!");
            }
        }
    }

    private static void dodajProdukt() {
        System.out.print("Nazwa produktu: ");
        String nazwa = scanner.nextLine();
        System.out.print("Cena: ");
        double cena = scanner.nextDouble();
        System.out.print("Ilość: ");
        int ilosc = scanner.nextInt();

        magazyn.dodajProdukt(new Produkt(nazwa, cena, ilosc));
        System.out.println("Produkt dodany pomyślnie!");
    }

    private static void edytujProdukt() {
        magazyn.wyswietlProdukty();
        System.out.print("Wybierz ID produktu do edycji: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produkt produkt = magazyn.getProduktById(id);
        if (produkt == null) {
            System.out.println("Produkt o podanym ID nie istnieje!");
            return;
        }

        System.out.print("Nowa nazwa (" + produkt.getNazwa() + "): ");
        String nazwa = scanner.nextLine();
        System.out.print("Nowa cena (" + produkt.getCena() + "): ");
        double cena = scanner.nextDouble();
        System.out.print("Nowa ilość (" + produkt.getIlosc() + "): ");
        int ilosc = scanner.nextInt();

        produkt.setNazwa(nazwa.isEmpty() ? produkt.getNazwa() : nazwa);
        produkt.setCena(cena > 0 ? cena : produkt.getCena());
        produkt.setIlosc(ilosc >= 0 ? ilosc : produkt.getIlosc());

        System.out.println("Produkt zaktualizowany pomyślnie!");
    }

    private static void usunProdukt() {
        magazyn.wyswietlProdukty();
        System.out.print("Wybierz ID produktu do usunięcia: ");
        int id = scanner.nextInt();

        if (magazyn.usunProdukt(id)) {
            System.out.println("Produkt usunięty pomyślnie!");
        } else {
            System.out.println("Nie udało się usunąć produktu!");
        }
    }

    private static void zarzadzajKlientami() {
        boolean powrot = false;
        while (!powrot) {
            System.out.println("\n=== ZARZĄDZANIE KLIENTAMI ===");
            System.out.println("1. Wyświetl wszystkich klientów");
            System.out.println("2. Dodaj klienta");
            System.out.println("3. Edytuj klienta");
            System.out.println("4. Usuń klienta");
            System.out.println("5. Powrót");
            System.out.print("Wybierz opcję: ");

            int wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    wyswietlKlientow();
                    break;
                case 2:
                    dodajKlienta();
                    break;
                case 3:
                    edytujKlienta();
                    break;
                case 4:
                    usunKlienta();
                    break;
                case 5:
                    powrot = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór!");
            }
        }
    }

    private static void wyswietlKlientow() {
        if (klienci.isEmpty()) {
            System.out.println("Brak klientów w systemie!");
            return;
        }

        System.out.println("\nLISTA KLIENTÓW:");
        for (int i = 0; i < klienci.size(); i++) {
            System.out.println((i + 1) + ". " + klienci.get(i));
        }
    }

    private static void dodajKlienta() {
        System.out.println("Typ klienta:");
        System.out.println("1. Zwykły klient");
        System.out.println("2. Stały klient");
        System.out.print("Wybierz: ");
        int typ = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Imię i nazwisko: ");
        String imieNazwisko = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Saldo portfela: ");
        double portfel = scanner.nextDouble();

        if (typ == 1) {
            klienci.add(new Klient(imieNazwisko, email, portfel));
        } else {
            System.out.print("Rabat (%): ");
            double rabat = scanner.nextDouble() / 100;
            klienci.add(new KlientStaly(imieNazwisko, email, portfel, rabat));
        }

        System.out.println("Klient dodany pomyślnie!");
    }

    private static void edytujKlienta() {
        wyswietlKlientow();
        System.out.print("Wybierz numer klienta do edycji: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= klienci.size()) {
            System.out.println("Nieprawidłowy numer klienta!");
            return;
        }

        Klient klient = klienci.get(index);

        System.out.print("Nowe imię i nazwisko (" + klient.getImieNazwisko() + "): ");
        String imieNazwisko = scanner.nextLine();
        System.out.print("Nowy email (" + klient.getEmail() + "): ");
        String email = scanner.nextLine();
        System.out.print("Nowe saldo (" + klient.getPortfel() + "): ");
        double portfel = scanner.nextDouble();

        klient.setImieNazwisko(imieNazwisko.isEmpty() ? klient.getImieNazwisko() : imieNazwisko);
        klient.setEmail(email.isEmpty() ? klient.getEmail() : email);
        klient.setPortfel(portfel >= 0 ? portfel : klient.getPortfel());

        if (klient instanceof KlientStaly) {
            System.out.print("Nowy rabat (%): ");
            double rabat = scanner.nextDouble() / 100;
            ((KlientStaly) klient).setRabat(rabat);
        }

        System.out.println("Klient zaktualizowany pomyślnie!");
    }

    private static void usunKlienta() {
        wyswietlKlientow();
        System.out.print("Wybierz numer klienta do usunięcia: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < klienci.size()) {
            klienci.remove(index);
            System.out.println("Klient usunięty pomyślnie!");
        } else {
            System.out.println("Nieprawidłowy numer klienta!");
        }
    }

    private static void sprzedazDetaliczna() {
        System.out.println("\n=== SPRZEDAŻ DETALICZNA ===");
        Klient tempKlient = new Klient("Klient detaliczny", "brak", 0);
        przetwarzajSprzedaz(tempKlient);
    }

    private static void sprzedazHurtowa() {
        System.out.println("\n=== SPRZEDAŻ HURTOWA ===");
        wyswietlKlientow();
        System.out.print("Wybierz numer klienta: ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= klienci.size()) {
            System.out.println("Nieprawidłowy numer klienta!");
            return;
        }

        przetwarzajSprzedaz(klienci.get(index));
    }

    private static void przetwarzajSprzedaz(Klient klient) {
        Koszyk koszyk = new Koszyk(klient);
        boolean zakupy = true;

        while (zakupy) {
            System.out.println("\nDostępne produkty:");
            magazyn.wyswietlProdukty();
            System.out.println("1. Dodaj produkt do koszyka");
            System.out.println("2. Usuń produkt z koszyka");
            System.out.println("3. Wyświetl koszyk");
            System.out.println("4. Zakończ zakupy");
            System.out.print("Wybierz opcję: ");

            int wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    dodajDoKoszyka(koszyk);
                    break;
                case 2:
                    usunZKoszyka(koszyk);
                    break;
                case 3:
                    koszyk.wyswietlKoszyk();
                    break;
                case 4:
                    zakupy = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór!");
            }
        }

        // Finalizacja transakcji
        try {
            double suma = koszyk.obliczSuma();
            System.out.printf("\nDo zapłaty: %.2f zł\n", suma);
            System.out.printf("Saldo klienta: %.2f zł\n", klient.getPortfel());

            if (klient.getPortfel() >= suma) {
                klient.setPortfel(klient.getPortfel() - suma);
                magazyn.aktualizujStan(koszyk);
                System.out.println("Transakcja zakończona pomyślnie!");
            } else {
                System.out.println("Niewystarczające środki na koncie!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    private static void dodajDoKoszyka(Koszyk koszyk) {
        System.out.print("Podaj ID produktu: ");
        int id = scanner.nextInt();
        System.out.print("Podaj ilość: ");
        int ilosc = scanner.nextInt();

        try {
            Produkt produkt = magazyn.getProduktById(id);
            if (produkt == null) {
                System.out.println("Produkt o podanym ID nie istnieje!");
                return;
            }

            koszyk.dodajPozycje(produkt, ilosc);
            System.out.println("Produkt dodany do koszyka!");
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    private static void usunZKoszyka(Koszyk koszyk) {
        koszyk.wyswietlKoszyk();
        if (koszyk.czyPusty()) return;

        System.out.print("Podaj numer pozycji do usunięcia: ");
        int index = scanner.nextInt() - 1;

        try {
            koszyk.usunPozycje(index);
            System.out.println("Produkt usunięty z koszyka!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nieprawidłowy numer pozycji!");
        }
    }
}