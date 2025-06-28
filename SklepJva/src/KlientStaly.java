public class KlientStaly extends Klient {
    private double rabat;

    public KlientStaly(String imieNazwisko, String email, double portfel, double rabat) {
        super(imieNazwisko, email, portfel);
        this.rabat = rabat;
    }

    // Gettery i settery
    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        if (rabat >= 0 && rabat <= 1) {
            this.rabat = rabat;
        }
    }

    // Przesłonięta metoda toString()
    @Override
    public String toString() {
        return super.toString() + String.format(", Rabat: %.0f%%", rabat * 100);
    }
}