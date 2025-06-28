public class Klient {
    private String imieNazwisko;
    private String email;
    private double portfel;

    public Klient(String imieNazwisko, String email, double portfel) {
        this.imieNazwisko = imieNazwisko;
        this.email = email;
        this.portfel = portfel;
    }

    // Gettery i settery
    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        if (imieNazwisko != null && !imieNazwisko.isEmpty()) {
            this.imieNazwisko = imieNazwisko;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
    }

    public double getPortfel() {
        return portfel;
    }

    public void setPortfel(double portfel) {
        if (portfel >= 0) {
            this.portfel = portfel;
        }
    }

    @Override
    public String toString() {
        return String.format("%s, Email: %s, Saldo: %.2f zł", imieNazwisko, email, portfel);
    }
}