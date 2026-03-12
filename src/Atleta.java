public abstract class Atleta {
    private String nome;
    private int idade;
    private int medalhas;

    public Atleta(String nome, int idade, int medalhas) {
        this.nome = nome;
        this.idade = idade;
        this.medalhas = medalhas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getMedalhas() {
        return medalhas;
    }

    public void setMedalhas(int medalhas) {
        this.medalhas = medalhas;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return nome + " tem " + idade + " anos e " + medalhas + " medalhas";
    }
}