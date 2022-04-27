package entidades;

import java.time.LocalDate;

// 1 – Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
public class Pessoa {

    private String nome;

    private LocalDate nascimento;


    public Pessoa(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}
