public class AtletaPista extends Atleta {
    private double melhorTempo; // em segundos
    private int distancia; // em metros

    public AtletaPista(String nome, int idade, int medalhas, double melhorTempo, int distancia) {
        super(nome, idade, medalhas);
        this.melhorTempo = melhorTempo;
        this.distancia = distancia;
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

    @Override
    public String getTipo() {
        return "Pista";
    }

    @Override
    public String toString() {
        return super.toString() + " - Atleta de Pista " + distancia + "m - Melhor tempo: " + melhorTempo + "s";
    }
}
