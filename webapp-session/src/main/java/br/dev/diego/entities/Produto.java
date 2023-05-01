package br.dev.diego.entities;

import java.time.LocalDate;

public class Produto {

    private Long id;
    private String nome;
    private Categoria categoria;
    private int preco;
    private String sku;
    private LocalDate dataRegistro;

    public Produto() {
    }

    public Produto(Long id, String nome, Long categoriaId, int preco, String sku, LocalDate dataRegistro) {
        this.id = id;
        this.nome = nome;
        this.categoria = new Categoria(categoriaId);
        this.preco = preco;
        this.sku = sku;
        this.dataRegistro = dataRegistro;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
