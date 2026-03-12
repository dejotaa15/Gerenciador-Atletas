public class AtletaForca extends Atleta {
    private double maiorPeso; // em quilogramas

    public AtletaForca(String nome, int idade, int medalhas, double maiorPeso) {
        super(nome, idade, medalhas);
        this.maiorPeso = maiorPeso;
    }

    public double getMaiorPeso() {
        return maiorPeso;
    }

    public void setMaiorPeso(double maiorPeso) {
        this.maiorPeso = maiorPeso;
    }

    @Override
    public String getTipo() {
        return "Força";
    }

    @Override
    public String toString() {
        return super.toString() + " - Atleta de Força - Maior peso: " + maiorPeso + "kg";
    }
}