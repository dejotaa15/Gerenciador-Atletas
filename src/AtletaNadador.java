public class AtletaNadador extends Atleta {
    private double melhorTempo; // em segundos
    private int distancia;      // em metros
    private String estilo;

    public AtletaNadador(String nome, int idade, int medalhas, double melhorTempo, int distancia, String estilo) {
        super(nome, idade, medalhas);
        this.melhorTempo = melhorTempo;
        this.distancia = distancia;
        this.estilo = estilo;
    }

    public double getMelhorTempo() {
        return melhorTempo;
    }

    public void setMelhorTempo(double melhorTempo) {
        this.melhorTempo = melhorTempo;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public String getTipo() {
        return "Natação";
    }

    @Override
    public String toString() {
        return super.toString()
                + " - Atleta de Natação - " + distancia + "m - " + estilo
                + " - Melhor tempo: " + melhorTempo + "s";
    }
}
