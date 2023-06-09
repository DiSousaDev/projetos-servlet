package br.dev.diego.entities;

public class Produto {

    private Long id;
    private String nome;
    private String tipo;
    private int preco;

    public Produto() {
    }

    public Produto(Long id, String nome, String tipo, int preco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
