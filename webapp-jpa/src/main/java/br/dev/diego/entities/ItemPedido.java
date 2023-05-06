package br.dev.diego.entities;

import java.util.Objects;

public class ItemPedido {

    private Integer quantidade;
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getValorTotal() {
        return quantidade * produto.getPreco();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido that)) return false;
        return Objects.equals(produto.getId(), that.produto.getId())
                && Objects.equals(produto.getNome(), that.produto.getNome());
    }

}
