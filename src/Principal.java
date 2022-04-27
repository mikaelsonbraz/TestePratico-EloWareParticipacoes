import entidades.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 3 – Deve conter uma classe Principal para executar as seguintes ações:
public class Principal {

    public Principal(List<Funcionario> funcionarios, HashMap<String, List<String>> mapaFuncoes) {
        this.funcionarios = funcionarios;
        this.mapaFuncoes = mapaFuncoes;
    }

    DecimalFormat formato = new DecimalFormat("#,###.00");
    DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    List<Funcionario> funcionarios = new ArrayList<>();
    HashMap<String, List<String>> mapaFuncoes = new HashMap<>();

    public void inserirFuncionario(String nome, LocalDate nascimento, BigDecimal salario, String funcao){
        Funcionario funcionario = new Funcionario(nome, nascimento, salario, funcao);
        funcionarios.add(funcionario);
    }

    public void removerFuncionario(String nome){
        funcionarios.removeIf(funcionario -> Objects.equals(funcionario.getNome(), nome));
    }

    public void exibirFuncionarios(){
        System.out.println("----------------------------------------------------------");
        System.out.printf("%10s %15s %15s %10s", "NOME", "NASCIMENTO", "SALÁRIO", "FUNÇÃO\n");
        System.out.println("----------------------------------------------------------");
        for(Funcionario funcionario : this.funcionarios){
            System.out.printf("%10s %15s %15s %10s", funcionario.getNome(), funcionario.getNascimento().format(dataFormatada), formato.format(funcionario.getSalario()), funcionario.getFuncao());
            System.out.println("");
        }
        System.out.println("----------------------------------------------------------");
    }

    public void reajusteSalarial(Integer porcentagem){
        for (Funcionario funcionario : this.funcionarios){
            funcionario.setSalario(funcionario.getSalario()
                    .add(funcionario.getSalario()
                            .multiply(BigDecimal.valueOf(porcentagem))
                            .divide(BigDecimal.valueOf(100))));
        }
    }

    public void agruparFuncionarios(){
        List<String> operadores = new ArrayList<>();
        List<String> coordenadores = new ArrayList<>();
        List<String> diretores = new ArrayList<>();
        List<String> recepcionistas = new ArrayList<>();
        List<String> contadores = new ArrayList<>();
        List<String> gerentes = new ArrayList<>();
        List<String> eletricistas = new ArrayList<>();

        for (Funcionario funcionario : this.funcionarios){
            switch (funcionario.getFuncao()) {
                case "Operador" -> operadores.add(funcionario.toString());
                case "Coordenador" -> coordenadores.add(funcionario.toString());
                case "Diretor" -> diretores.add(funcionario.toString());
                case "Recepcionista" -> recepcionistas.add(funcionario.toString());
                case "Contador" -> contadores.add(funcionario.toString());
                case "Gerente" -> gerentes.add(funcionario.toString());
                case "Eletricista" -> eletricistas.add(funcionario.toString());
            }
        }

        this.mapaFuncoes.put("Operadores", operadores);
        this.mapaFuncoes.put("Coordenadores", coordenadores);
        this.mapaFuncoes.put("Diretores", diretores);
        this.mapaFuncoes.put("Recepcionistas", recepcionistas);
        this.mapaFuncoes.put("Contadores", contadores);
        this.mapaFuncoes.put("Gerentes", gerentes);
        this.mapaFuncoes.put("Eletricistas", eletricistas);
    }

    public void mostrarPorFuncao(){
        for (Map.Entry<String, List<String>> par : mapaFuncoes.entrySet()){
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }

    public void verificarAniversario(int mes){
        for (Funcionario funcionario : this.funcionarios){
            if (Objects.equals(funcionario.getNascimento().getMonthValue(), mes)){
                System.out.println("O(a) funcionário(a) " + funcionario.getNome() + " completa aniversário no mês " + mes);
            }
        }
    }

    public void verificarMaiorIdade(){
        LocalDate maiorIdade = LocalDate.now();
        String nome = "";
        int idade = 0;
        for(Funcionario funcionario : this.funcionarios){
            if(funcionario.getNascimento().isBefore(maiorIdade)){
                maiorIdade = funcionario.getNascimento();
                nome = funcionario.getNome();
                idade = Period.between(funcionario.getNascimento(), LocalDate.now()).getYears();
            }
        }
        System.out.println("O(a) funcionário(a) com a maior idade é " + nome + ", com " + idade + " anos.");
    }

    public void exibirPorOrdemAlfabetica(){
        List<String> alfabetica = new ArrayList<>();
        for(Funcionario funcionario : this.funcionarios){
            String dado = funcionario.getNome() + ", "
                    + funcionario.getNascimento().format(dataFormatada) + ", "
                    + formato.format(funcionario.getSalario()) + ", "
                    + funcionario.getFuncao();
            alfabetica.add(dado);
        }
        Collections.sort(alfabetica);
        for(String dado : alfabetica){
            System.out.println(dado);
        }
    }

    public void totalDosSalarios(){
        BigDecimal somaSalarios = new BigDecimal(0);
        for (Funcionario funcionario : this.funcionarios){
            somaSalarios = somaSalarios.add(funcionario.getSalario());
        }
        System.out.println("O valor total dos salários somados é de R$ " + formato.format(somaSalarios));
    }

    public void quantSalariosMinimos(){
        for (Funcionario funcionario : funcionarios){
            double minimos = funcionario.getSalario().doubleValue() / 1212;
            System.out.println("O(a) funcionário(a) " + funcionario.getNome() + " recebe " + String.format("%.2f", minimos) + " salários mínimos");
        }
    }


    public static void main(String[] args) {

        Principal principal = new Principal(new ArrayList<>(), new HashMap<>());

        //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima
        principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador");
        principal.inserirFuncionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.84), "Operador");
        principal.inserirFuncionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador");
        principal.inserirFuncionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor");
        principal.inserirFuncionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista");
        principal.inserirFuncionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador");
        principal.inserirFuncionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador");
        principal.inserirFuncionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente");
        principal.inserirFuncionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista");
        principal.inserirFuncionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente");

        //3.2 – Remover o funcionário “João” da lista.
        principal.removerFuncionario("João");

        /*
        3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
         */
        principal.exibirFuncionarios();

        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        principal.reajusteSalarial(10);

        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        principal.agruparFuncionarios();

        //3.6 – Imprimir os funcionários, agrupados por função.
        principal.mostrarPorFuncao();

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        principal.verificarAniversario(10);
        principal.verificarAniversario(12);

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        principal.verificarMaiorIdade();

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        principal.exibirPorOrdemAlfabetica();

        //3.11 – Imprimir o total dos salários dos funcionários.
        principal.totalDosSalarios();

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        principal.quantSalariosMinimos();
    }
}
