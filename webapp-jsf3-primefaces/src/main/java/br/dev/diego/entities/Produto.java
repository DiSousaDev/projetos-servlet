package br.dev.diego.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Informe um nome, campo obrigatório.")
    private String nome;
    @NotNull(message = "Informe o preço, campo obrigatório.")
    private Double preco;
    @NotEmpty(message = "Informe o sku, campo obrigatório.")
    private String sku;
    @Column(name = "data_registro")
    @NotNull(message = "Informe o Data de Registro, campo obrigatório.")
    private LocalDate dataRegistro;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Informe o categoria, campo obrigatório.")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(Long id, String nome, Double preco, String sku, LocalDate dataRegistro) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.sku = sku;
        this.dataRegistro = dataRegistro;
    }

//    @PrePersist
//    public void prePersist() {
//        dataRegistro = LocalDate.now();
//    }

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
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

    public void setDataRegistro(LocalDate dataCadastro) {
        this.dataRegistro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", sku='" + sku + '\'' +
                ", dataRegistro=" + dataRegistro +
                '}';
    }
}
