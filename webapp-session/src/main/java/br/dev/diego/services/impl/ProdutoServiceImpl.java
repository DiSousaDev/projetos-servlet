package br.dev.diego.services.impl;

import br.dev.diego.entities.Produto;
import br.dev.diego.services.ProdutoService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProdutoServiceImpl implements ProdutoService {
    @Override
    public List<Produto> listarProdutos() {
        return Arrays.asList(
                new Produto(1L, "Notebook", "computacao", 3500),
                new Produto(2L, "Mesa de Escritório", "Oficina", 1200),
                new Produto(3L, "Teclado Mecânico", "computacao", 289)
        );
    }

    @Override
    public Optional<Produto> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return Optional.empty();
        }
        List<Produto> produtos = listarProdutos();
        return produtos.stream().filter(produto -> produto.getNome().toUpperCase()
                .contains(nome.toUpperCase())).findFirst();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        if (id == null || id == 0) {
            return Optional.empty();
        }
        List<Produto> produtos = listarProdutos();
        return produtos.stream().filter(produto -> Objects.equals(produto.getId(), id)).findFirst();
    }

}
