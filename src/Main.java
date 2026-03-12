import javax.swing.*;
import java.util.*;

public class Main  {
    private List<Atleta> atletas;
    private JFrame frame;

    public Main() {
        atletas = new ArrayList<>();
        inicializarDados();
        criarInterface();
    }

    private void inicializarDados() {
        atletas.add(new AtletaPista("João Silva", 25, 5, 11.2, 100));
        atletas.add(new AtletaPista("Maria Santos", 28, 8, 24.5, 200));
        atletas.add(new AtletaPista("Carlos Oliveira", 30, 3, 50.2, 400));
        atletas.add(new AtletaPista("Ana Costa", 26, 6, 1.58, 1500));
        atletas.add(new AtletaForca("Pedro Souza", 32, 4, 180.5));
        atletas.add(new AtletaForca("Bruno Lima", 29, 7, 220.0));
        atletas.add(new AtletaForca("Fernanda Alves", 27, 5, 95.0));
        atletas.add(new AtletaNadador("Gustavo", 19, 2, 35, 200, "Crau"));
    }

    private void criarInterface() {
        frame = new JFrame("Gerenciador de Atletas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Visualizar", criarPainelVisualizacao());
        abas.addTab("Buscar", criarPainelBusca());
        abas.addTab("Cadastrar", criarPainelCadastro());
        abas.addTab("Estatísticas", criarPainelEstatisticas());
        abas.addTab("Ranking", criarPainelRanking());

        frame.add(abas);
        frame.setVisible(true);
    }

    private JPanel criarPainelVisualizacao() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.addActionListener(e -> {
            textArea.setText("");
            atletas.forEach(a -> textArea.append(a.toString() + "\n"));
        });

        painel.add(new JLabel("Todos os Atletas:"));
        painel.add(scrollPane);
        painel.add(botaoAtualizar);

        botaoAtualizar.doClick();
        return painel;
    }

    private JPanel criarPainelBusca() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel labelNome = new JLabel("Digite o nome do atleta:");
        JTextField campoNome = new JTextField(20);
        JTextArea resultado = new JTextArea(10, 40);
        resultado.setEditable(false);
        resultado.setLineWrap(true);
        resultado.setWrapStyleWord(true);

        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.addActionListener(e -> {
            String nome = campoNome.getText().trim().toLowerCase();
            resultado.setText("");

            List<Atleta> encontrados = atletas.stream()
                    .filter(a -> a.getNome().toLowerCase().contains(nome))
                    .toList();

            if (encontrados.isEmpty()) {
                resultado.setText("Nenhum atleta encontrado com: " + nome);
            } else {
                encontrados.forEach(a -> resultado.append(a.toString() + "\n"));
            }
        });

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(botaoBuscar);
        painel.add(new JScrollPane(resultado));

        return painel;
    }

    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(20);

        JLabel labelIdade = new JLabel("Idade:");
        JTextField campoIdade = new JTextField(5);

        JLabel labelMedalhas = new JLabel("Medalhas:");
        JTextField campoMedalhas = new JTextField(5);

        JLabel labelTipo = new JLabel("Tipo:");
        String[] tipos = {"Pista", "Força", "Natação"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        JLabel labelTempo = new JLabel("Melhor Tempo (segundos):");
        JTextField campoTempo = new JTextField(10);
        campoTempo.setVisible(true);

        JLabel labelDistancia = new JLabel("Distância (metros):");
        JTextField campoDistancia = new JTextField(10);
        campoDistancia.setVisible(true);

        JLabel labelPeso = new JLabel("Maior Peso (kg):");
        JTextField campoPeso = new JTextField(10);
        campoPeso.setVisible(false);

        JLabel labelEstilo = new JLabel("Estilo (Natação):");
        JTextField campoEstilo = new JTextField(10);

        comboTipo.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            boolean isPista    = "Pista".equals(tipo);
            boolean isForca    = "Força".equals(tipo);
            boolean isNatacao  = "Natação".equals(tipo);

            labelTempo.setVisible(isPista || isNatacao);
            campoTempo.setVisible(isPista || isNatacao);
            labelDistancia.setVisible(isPista || isNatacao);
            campoDistancia.setVisible(isPista || isNatacao);

            labelPeso.setVisible(isForca);
            campoPeso.setVisible(isForca);

            labelEstilo.setVisible(isNatacao);
            campoEstilo.setVisible(isNatacao);
        });

        JTextArea msgResultado = new JTextArea(3, 40);
        msgResultado.setEditable(false);
        msgResultado.setLineWrap(true);
        msgResultado.setWrapStyleWord(true);

        JButton botaoCadastrar = new JButton("Cadastrar Atleta");
        botaoCadastrar.addActionListener(e -> {
            try {
                String nome = campoNome.getText().trim();
                int idade = Integer.parseInt(campoIdade.getText());
                int medalhas = Integer.parseInt(campoMedalhas.getText());
                String tipo = (String) comboTipo.getSelectedItem();

                if (nome.isEmpty()) {
                    msgResultado.setText("Erro: Nome não pode estar vazio!");
                    return;
                }

                if(tipo.equals("Pista")) {
                    int distancia = Integer.parseInt(campoDistancia.getText());
                    double tempo = Double.parseDouble(campoTempo.getText());
                    atletas.add(new AtletaPista(nome, idade, medalhas, tempo, distancia));
                } else if(tipo.equals("Força")) {
                    double peso = Double.parseDouble(campoPeso.getText());
                    atletas.add(new AtletaForca(nome, idade, medalhas, peso));
                } else if(tipo.equals("Natação")) {
                    int distancia = Integer.parseInt(campoDistancia.getText());
                    double tempo = Double.parseDouble(campoTempo.getText());
                    String estilo = campoEstilo.getText().trim();
                    atletas.add(new AtletaNadador(nome, idade, medalhas, tempo, distancia, estilo));
                }

                msgResultado.setText("Atleta cadastrado com sucesso!");
                campoNome.setText("");
                campoIdade.setText("");
                campoMedalhas.setText("");
                campoTempo.setText("");
                campoDistancia.setText("");
                campoPeso.setText("");
                campoEstilo.setText("");
            } catch (NumberFormatException ex) {
                msgResultado.setText("Erro com algum valor");
            }
        });

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelIdade);
        painel.add(campoIdade);
        painel.add(labelEstilo);
        painel.add(campoEstilo);
        painel.add(labelMedalhas);
        painel.add(campoMedalhas);
        painel.add(labelTipo);
        painel.add(comboTipo);
        painel.add(labelTempo);
        painel.add(campoTempo);
        painel.add(labelDistancia);
        painel.add(campoDistancia);
        painel.add(labelPeso);
        painel.add(campoPeso);
        painel.add(Box.createVerticalStrut(10));
        painel.add(botaoCadastrar);
        painel.add(new JScrollPane(msgResultado));

        return painel;
    }

    private JPanel criarPainelEstatisticas() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JTextArea stats = new JTextArea();
        stats.setEditable(false);
        stats.setLineWrap(true);
        stats.setWrapStyleWord(true);
        stats.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));

        JButton botaoAtualizar = new JButton("Atualizar Estatísticas");
        botaoAtualizar.addActionListener(e -> {
            stats.setText("");

            long totalAtletas = atletas.size();
            long atletasPista = atletas.stream().filter(a -> a instanceof AtletaPista).count();
            long atletasForca = atletas.stream().filter(a -> a instanceof AtletaForca).count();
            long atletasNatacao = atletas.stream().filter(a -> a instanceof AtletaNadador).count();

            stats.append("=== ESTATÍSTICAS GERAIS ===\n\n");
            stats.append("Total de Atletas: " + totalAtletas + "\n");
            stats.append("  - Atletas de Pista: " + atletasPista + "\n");
            stats.append("  - Atletas de Força: " + atletasForca + "\n");
            stats.append("  - Atletas de Natação: " + atletasNatacao + "\n\n");

            int somaMedalhas = atletas.stream()
                    .mapToInt(Atleta::getMedalhas)
                    .sum();
            stats.append("=== MEDALHAS ===\n");
            stats.append("Soma Total de Medalhas: " + somaMedalhas + "\n");
            stats.append("Média de Medalhas: " + String.format("%.2f",
                    atletas.stream().mapToInt(Atleta::getMedalhas).average().orElse(0)) + "\n\n");

            double mediaTempos = atletas.stream()
                    .filter(a -> a instanceof AtletaPista)
                    .mapToDouble(a -> ((AtletaPista) a).getMelhorTempo())
                    .average()
                    .orElse(0);
            stats.append("=== ATLETAS DE PISTA ===\n");
            stats.append("Média do Melhor Tempo: " + String.format("%.2f", mediaTempos) + " segundos\n\n");

            double mediaTemposNatacao = atletas.stream()
                    .filter(a -> a instanceof AtletaNadador)
                    .mapToDouble(a -> ((AtletaNadador) a).getMelhorTempo())
                    .average()
                    .orElse(0);
            stats.append("=== ATLETAS DE NATAÇÃO ===\n");
            stats.append("Média do Melhor Tempo: " + String.format("%.2f", mediaTemposNatacao) + " segundos\n\n");

            double mediaPesos = atletas.stream()
                    .filter(a -> a instanceof AtletaForca)
                    .mapToDouble(a -> ((AtletaForca) a).getMaiorPeso())
                    .average()
                    .orElse(0);
            stats.append("=== ATLETAS DE FORÇA ===\n");
            stats.append("Média do Maior Peso: " + String.format("%.2f", mediaPesos) + " kg\n");
        });

        painel.add(botaoAtualizar);
        painel.add(new JScrollPane(stats));

        botaoAtualizar.doClick();
        return painel;
    }

    private JPanel criarPainelRanking() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JTextArea ranking = new JTextArea();
        ranking.setEditable(false);
        ranking.setLineWrap(true);
        ranking.setWrapStyleWord(true);

        JButton botaoAtualizar = new JButton("Atualizar Ranking");
        botaoAtualizar.addActionListener(e -> {
            ranking.setText("");

            ranking.append("=== RANKING DE MEDALHAS ===\n\n");
            ranking.append("Posição | Nome | Medalhas\n");
            ranking.append("--------------------------------------------------\n");

            atletas.stream()
                    .sorted((a1, a2) -> Integer.compare(a2.getMedalhas(), a1.getMedalhas()))
                    .forEach(a -> ranking.append(String.format("%d. %-20s %d\n",
                            atletas.stream().filter(x -> x.getMedalhas() >= a.getMedalhas()).count(),
                            a.getNome(),
                            a.getMedalhas())));
        });

        painel.add(botaoAtualizar);
        painel.add(new JScrollPane(ranking));

        botaoAtualizar.doClick();
        return painel;
    }

    public static void main(String[] args) {
        Main janela = new Main();
    }
}